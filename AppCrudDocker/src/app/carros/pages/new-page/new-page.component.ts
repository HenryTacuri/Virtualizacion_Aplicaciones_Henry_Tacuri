import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { Carro } from '../../interfaces/carro.interface';
import { CarrosServices } from '../../services/carros-services.service';
import { ModalService } from 'src/app/services/modal-service.service';

@Component({
  selector: 'app-new-page',
  templateUrl: './new-page.component.html',
  styleUrls: ['./new-page.component.css']
})
export class NewPageComponent implements OnInit{

  public mensaje!: string;
  public modalSwitch?: boolean;

  public carro!: Carro;

  public myForm: FormGroup = this.fb.group({
    marca: ['', [Validators.required, Validators.minLength(1)],],
    modelo: ['', [Validators.required, Validators.minLength(1)]],
    color: ['', [Validators.required, Validators.minLength(1)]],
    anio: ['', [Validators.required, Validators.min(1)]],
    cilindraje: ['', [Validators.required, Validators.min(1)]],
    precio: ['', [Validators.required, Validators.min(1)]],
  });

  constructor(
    private fb: FormBuilder,
    private carrosServices: CarrosServices,
    private modalService: ModalService
  ) {}

  ngOnInit(): void {
    this.modalService.modal.subscribe((valor) => {this.modalSwitch = valor});
  }


  public onSubmit() {
    if(this.myForm.invalid) {

      this.mensaje = "Formulario Invalido";
      this.openModal();
      this.ngOnInit();
    } else {
      this.carro = this.myForm.value;

      this.carrosServices.guardarCarro(this.carro).subscribe(resp => {
        console.log({'estado': resp});

        this.mensaje = "Carro Guardado";
        this.openModal();
        this.ngOnInit();
        this.myForm.reset();
      });
    }
  }

  //Metodo para abrir el modal
  openModal() {
    this.modalSwitch = true;
  }

}


/*
    this.carrosServices.actualizarCarro(this.carro).subscribe(resp => {
      console.log({'estado': resp});
    });

    this.myForm.reset();

*/


/*
    this.carro = {
      codigo: 2,
      marca: 'fsa',
      modelo: 'fdsa',
      color: 'aa',
      anio: 2002,
      cilindraje: 22,
      precio: 444,
    }

    const {codigo, ...carroPre} = this.carro;

    this.carro = carroPre;
*/

