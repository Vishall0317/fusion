import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  imports: [
    FormsModule, 
    MatFormFieldModule,
    MatInputModule,
    CommonModule, 
  ],
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  activeSection: any;
  dbName: any;
  tableName: any;
  primaryKey: any;
  id: any;
  mongoDbName: any;
  collection: any;
  primaryMongoKey: any;
  primaryMongoValue: any;
  consumerGroup: string = '';
  topic: string = '';
  partition: string = '';
  offset: string = '';
  kafkaData: any;
  dataVisible: boolean = false;
  data: any;
  mongo_Data: any;
  currentKafkaDataView: string | null = null;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

  // Show specific div based on button click
  showKafkaData(view: string) {
    this.currentKafkaDataView = view; // Set which div to show
  }

  // Show the respective section based on button clicked
  showSection(section: string) {
    this.activeSection = section;
  }

  // SQL Data fetch logic
  fetchData() {
    // Replace with your actual logic to fetch SQL data
    console.log('Fetching SQL data...');
    this.dataService.fetchData(this.dbName, this.tableName, this.primaryKey, this.id).subscribe(response => {
      this.data = response;
    });
  }

  // Mongo Data fetch logic
  fetchMongo_Data() {
    // Replace with your actual logic to fetch Mongo data
    console.log('Fetching Mongo data...');
    this.dataService.fetchMongo_Data(this.mongoDbName, this.collection, this.primaryMongoKey, this.primaryMongoValue).subscribe(response => {
      this.mongo_Data = response;
    });
  }

  // Kafka Data search logic (CG, Topic, Partition, Offset)
  searchingKafkaData() {
    console.log('Searching Kafka data...');
    this.dataVisible = true; // Show data
    this.dataService.searchingKafkaData(this.consumerGroup, this.topic, this.partition, this.offset).subscribe(response => {
      this.kafkaData = response;
    });
  }

  // Kafka Data logic (CG, Topic)
  fetchingKafkaData() {
    console.log('Fetching Kafka data...');
    this.dataVisible = true; // Show data
    this.dataService.fetchingKafkaData(this.consumerGroup, this.topic).subscribe(response => {
      console.log('Fetched Kafka data...', response);
      this.kafkaData = response;
    });
  }

  // Close the data output section
  closeData() {
    this.dataVisible = false;
    this.kafkaData = [];
  }
}
