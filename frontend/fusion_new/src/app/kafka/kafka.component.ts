import { Component, Input, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-kafka',
   imports: [
      FormsModule, 
      MatFormFieldModule,
      MatInputModule,
      CommonModule, 
    ],
  templateUrl: './kafka.component.html',
  styleUrl: './kafka.component.css'
})
export class KafkaComponent implements OnInit {
  
  activeSection: String =''; 
  consumerGroup: string = '';
  topic: string = '';
  partition: string = '';
  offset: string = '';
  kafkaData: any[] = [];
  kafkaDataByTopic: any[] = [];
  dataVisible: boolean = false;
  currentKafkaDataView: string | null = null;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

  // Show the respective section based on button clicked
  showSection(section: string) {
    this.kafkaDataByTopic = []; // Clear the data by topic when searching by partition and offset
    this.kafkaData = []; // Clear the data by partition and offset when searching by topic
    this.activeSection = section;
  }

  // Show specific div based on button click
  showKafkaData(view: string) {
    this.currentKafkaDataView = view; // Set which div to show
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
      this.kafkaDataByTopic = response;
    });
  }

  // Close the data output section
  closeData() {
    this.dataVisible = false;
    this.kafkaData = [];
    this.kafkaDataByTopic = [];
  }

}
