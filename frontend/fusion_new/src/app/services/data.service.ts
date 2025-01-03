import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private sqlApiUrl = 'http://localhost:8080/api/database/data';
  private mongoApiUrl = 'http://localhost:8080/api/mongo/data';

  private searchKafkaUrl = 'http://localhost:8080/kafka/search';
  private fetchKafkaUrl = 'http://localhost:8080/kafka/fetch';

  private fetchElasticSearchUrl = 'http://localhost:8080/elastic/search';

  constructor(private http: HttpClient) { }

  // dynamic database API 
  fetchSqlData(dbName: string, tableName: string, primaryKey: string, id: string): Observable<any> {
    const params = { dbName, tableName, primaryKey, id };
    return this.http.get(this.sqlApiUrl, { params });
  }

  fetchMongoData(dbName: string, collection: string, primaryKey: string, primaryValue: string): Observable<any> {
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

  fetchElasticSearchData(index: string, id: string): Observable<any> {
    const params = { index, id };
    return this.http.get(this.fetchElasticSearchUrl, {params});
  }

}
