import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private mongoByIdUrl = 'http://localhost:8080/api/mongo/';
  private mysqlByIdUrl = 'http://localhost:8080/api/mysql/';
  private mongoUrl = 'http://localhost:8080/api/mongo';
  private mysqlUrl = 'http://localhost:8080/api/mysql';

  private sqlApiUrl = 'http://localhost:8080/api/database/data';
  private mongoApiUrl = 'http://localhost:8080/api/mongo/data';

  private searchKafkaUrl = 'http://localhost:8080/kafka/search';
  private fetchKafkaUrl = 'http://localhost:8080/kafka/fetch';

  constructor(private http: HttpClient) { }

  getMongoData(id: string): Observable<any> {
    return this.http.get(`${this.mongoByIdUrl}${id}`);
  }

  getMySQLData(id: string): Observable<any> {
    return this.http.get(`${this.mysqlByIdUrl}${id}`);
  }

  getAllMongoData(): Observable<any> {
    return this.http.get(this.mongoUrl);
  }

  getAllMySQLData(): Observable<any> {
    return this.http.get(this.mysqlUrl);
  }

  // dynamic database API 
  fetchData(dbName: string, tableName: string, primaryKey: string, id: string): Observable<any> {
    const params = { dbName, tableName, primaryKey, id };
    return this.http.get(this.sqlApiUrl, { params });
  }

  fetchMongo_Data(dbName: string, collection: string, primaryKey: string, primaryValue: string): Observable<any> {
    const params = { dbName, collection, primaryKey, primaryValue };
    return this.http.get(this.mongoApiUrl, { params });
  }

  searchingKafkaData(consumerGroup: string, topic: string, partition: string, offset: string): Observable<any> {
    const params = { consumerGroup, topic, partition, offset };
    return this.http.get(this.searchKafkaUrl, { params });
  }

  fetchingKafkaData(consumerGroup: string, topic: string): Observable<any> {
    const params = { consumerGroup, topic };
    return this.http.get(this.fetchKafkaUrl, { params });
  }
}
