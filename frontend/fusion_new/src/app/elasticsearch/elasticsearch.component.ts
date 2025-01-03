import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-elasticsearch',
   imports: [
      FormsModule, 
      MatFormFieldModule,
      MatInputModule,
      CommonModule, 
    ],
  templateUrl: './elasticsearch.component.html',
  styleUrl: './elasticsearch.component.css'
})
export class ElasticsearchComponent implements OnInit {

  index: string = '';
  id: string = '';
  elasticData: any;
  

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

  fetchElasticSearchData() {
    this.dataService.fetchElasticSearchData(this.index, this.id).subscribe(response => {
      this.elasticData = response;
    });
  }

  closeData() {
    this.elasticData = null;
  }
}
