import { AbstractControl, FormControl } from "@angular/forms";

export function handleControl(control: AbstractControl | null) {
    if(control !== null && control !== undefined){
        return control;
      }
      return new FormControl(null);
}