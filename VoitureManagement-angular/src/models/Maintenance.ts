import { User } from "./User";
import { Vehicule } from "./Vehicule";

export interface Maintenance{
    id:number;
    date:Date;
    description:string;
    cout:number;
    vehiculeId:Vehicule;
    userId:User;
}