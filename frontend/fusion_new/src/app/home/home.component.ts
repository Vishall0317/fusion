import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import { SqlComponent } from "../sql/sql.component";
import { MongoComponent } from "../mongo/mongo.component";
import { KafkaComponent } from '../kafka/kafka.component';
import { ElasticsearchComponent } from "../elasticsearch/elasticsearch.component";
import { SplunkComponent } from '../splunk/splunk.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    CommonModule,
    SqlComponent,
    MongoComponent,
    KafkaComponent,
    ElasticsearchComponent,
    SplunkComponent
],
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  activeSection: String ='';

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
  }

  // Show the respective section based on button clicked
  showSection(section: string) {
    this.activeSection = section;
  }
}
