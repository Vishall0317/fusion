import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SqlComponent } from './sql/sql.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SqlComponent
  ],
  imports: [
    BrowserModule,
    NgModule,
    FormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule, 
    MatInputModule, 
    MatButtonModule,
    HttpClientModule
  ],
  exports: [
     // if used in other modules
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
