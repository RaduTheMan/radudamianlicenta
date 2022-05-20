import { Component } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'gemixque-ui';
  constructor(readonly iconRegistry: MatIconRegistry, readonly sanitizer: DomSanitizer) {
    iconRegistry.addSvgIcon('chevron-left', sanitizer.bypassSecurityTrustResourceUrl('assets/icons/chevron-left.svg'));
    iconRegistry.addSvgIcon('chevron-right', sanitizer.bypassSecurityTrustResourceUrl('assets/icons/chevron-right.svg'));
  }
}
