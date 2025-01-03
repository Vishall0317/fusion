import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-splunk',
   imports: [
      FormsModule, 
      MatFormFieldModule,
      MatInputModule,
      CommonModule, 
    ],
  templateUrl: './splunk.component.html',
  styleUrl: './splunk.component.css'
})
export class SplunkComponent implements OnInit {

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }
  
}
