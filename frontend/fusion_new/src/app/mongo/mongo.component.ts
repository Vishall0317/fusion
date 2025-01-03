import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-mongo',
   imports: [
      FormsModule, 
      MatFormFieldModule,
      MatInputModule,
      CommonModule, 
    ],
  templateUrl: './mongo.component.html',
  styleUrl: './mongo.component.css'
})
export class MongoComponent implements OnInit {

  activeSection: String ='';
  mongoDbName: any;
  collection: any;
  primaryMongoKey: any;
  primaryMongoValue: any;
  dataVisible: boolean = false;
  mongoData: any;
  currentMongoView: string | null = null;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

  showMongoData(view: string) {
    this.currentMongoView = view; // Set which div to show
  }

  // Show the respective section based on button clicked
  showSection(section: string) {
    this.activeSection = section;
  }

  // Mongo Data fetch logic
  fetchMongoData() {
    // Replace with your actual logic to fetch Mongo data
    console.log('Fetching Mongo data...');
    this.dataService.fetchMongoData(this.mongoDbName, this.collection, this.primaryMongoKey, this.primaryMongoValue).subscribe(response => {
      console.log('Fetched Mongo data...', response);
      this.mongoData = response;
    });
  }

  // Close the data output section
  closeData() {
    this.dataVisible = false;
    this.mongoData = [];
  }
}

