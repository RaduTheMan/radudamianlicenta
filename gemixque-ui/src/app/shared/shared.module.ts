import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { MaterialModule } from '../material';
import { GameSummaryComponent, HeaderComponent } from './components';
import { LoginDropdownComponent } from './components/header';

const components = [HeaderComponent, LoginDropdownComponent, GameSummaryComponent];
const exportedComponents = [HeaderComponent, GameSummaryComponent];

@NgModule({
    declarations: [...components],
    imports: [
        CommonModule,
        MaterialModule,
        AppRoutingModule,
        ReactiveFormsModule
    ],
    exports: [
        ...exportedComponents,
        MaterialModule
    ]
})
export class SharedModule {}