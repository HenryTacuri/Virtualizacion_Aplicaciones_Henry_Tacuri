import { Component, Input } from '@angular/core';
import { ModalService } from 'src/app/services/modal-service.service';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent {

    //Variable de entrada para mostrar el mensaje correspondiente en el modal
    @Input()
    public mensaje!: string;

    constructor(private modalService: ModalService) {}

    closeModal() {
      //Emitimos el valor false para cerrar el modal
      this.modalService.modal.emit(false);
    }

}
