import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sql',
  imports: [
    FormsModule, 
    MatFormFieldModule,
    MatInputModule,
    CommonModule, 
  ],
  templateUrl: './sql.component.html',
  styleUrl: './sql.component.css'
})
export class SqlComponent implements OnInit {

  activeSection: any;
  dbName: any;
  tableName: any;
  primaryKey: any;
  id: any;
  dataVisible: boolean = false;
  sqlData: any;
  currentSqlView: string | null = null;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

  showSqlData(view: string) {
    this.currentSqlView = view; // Set which div to show
  }

  // Show the respective section based on button clicked
  showSection(section: string) {
    this.activeSection = section;
  }

  // SQL Data fetch logic
  fetchSqlData() {
    // Replace with your actual logic to fetch SQL data
    console.log('Fetching SQL data...');
    this.dataService.fetchSqlData(this.dbName, this.tableName, this.primaryKey, this.id).subscribe(response => {
      console.log('Fetched SQL data...', response);
      this.sqlData = response;
    });
  }

  // Close the data output section
  closeData() {
    this.dataVisible = false;
    this.sqlData = [];
  }
}
