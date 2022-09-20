import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {ListagemUsuariosComponent} from "./components/listagem-usuarios/listagem-usuarios.component";
import {ListagemContasComponent} from "./components/listagem-contas/listagem-contas.component";
import {CadastroUsuarioComponent} from "./components/cadastro-usuario/cadastro-usuario.component";

const routes: Routes = [
  {
    path:'',
    component: HomeComponent
  },
  {
    path:'listagem-usuarios',
    component: ListagemUsuariosComponent
  },
  {
    path:'listagem-contas',
    component: ListagemContasComponent
  },
  {
    path:'cadastro-usuario',
    component:CadastroUsuarioComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
