import { Component, Input, OnInit } from '@angular/core';
import { Carro } from '../../interfaces/carro.interface';
import { CarrosServices } from '../../services/carros-services.service';
import { catchError, filter, map, of, switchMap, tap } from 'rxjs';
import { ModalService } from 'src/app/services/modal-service.service';

@Component({
  selector: 'carros-table-carros',
  templateUrl: './table-carros.component.html',
  styleUrls: ['./table-carros.component.css']
})
export class TableCarrosComponent implements OnInit {

  public mensaje!: string;
  public modalSwitch?: boolean;

  @Input()
  public carros: Carro[] = [];

  constructor(private carrosSevices: CarrosServices, private modalService: ModalService) { }

  ngOnInit(): void {
    this.modalService.modal.subscribe((valor) => {this.modalSwitch = valor});
  }

  public eliminarCarro(codigo: number) {

    this.carrosSevices.eliminarCarro(codigo).
    pipe(
      filter((result: boolean) => result),
      switchMap(() => this.carrosSevices.getCarros()),
      catchError(() => of([])),
      filter(nuevaLista => nuevaLista.length >= 0)
    ).subscribe( resp => {
      this.carros = resp;
      this.mensaje = "Carro eliminado";
      this.openModal();
      this.ngOnInit();
    });
  }

  //Metodo para abrir el modal
  openModal() {
    this.modalSwitch = true;
  }

}


/*
    this.carrosSevices.eliminarCarro(5).
    pipe(
      filter((result: boolean) => result),
      tap(r => console.log(r)),
      switchMap(() => this.carrosSevices.getCarros()),
      filter(nuevaLista => nuevaLista.length > 0)
    ).subscribe( resp => console.log(resp));
*/