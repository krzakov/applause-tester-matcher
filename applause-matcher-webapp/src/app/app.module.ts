import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';

import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {DeviceService} from './device.service';
import {CountryService} from "./country.service";
import {TestersService} from "./testers.service";
import { SearchFormComponent } from './component/search-form/search-form.component';
import { ResultComponent } from './component/result/result.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchFormComponent,
    ResultComponent
  ],
  imports: [
    BrowserModule,
    NgMultiSelectDropDownModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [
    DeviceService,
    CountryService,
    TestersService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
