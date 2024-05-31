import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  //Variable para emitir valores desde el modal.
  public modal: EventEmitter<any> = new EventEmitter<any>();

}
