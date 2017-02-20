package it.unisalento.distributori.util;

import java.util.*;

public class GeneraPwd {
	
	int dim;//dimensione password
    
    /** Creates a new instance of GeneraPwd */
    public GeneraPwd(int dim) {
    	this.dim=dim;  
    }
    
	Random rnd = new Random();
	
    public String getPWD() {

        //decido la quantità di cifre numeriche
        int num = rnd.nextInt(dim-4)+1;
        num= num+2;
        //decido la quantità di cifre alfa
        int alfa = dim-num;
        int appoggio=0;
        String pass="";
        int alterna=0;
        int contnum=1;
        int contalfa=1;
        for (int i=1;i<=dim;i++) {
            alterna = rnd.nextInt(2);
            if (contalfa>alfa){alterna=0;}
            else{if (contnum>num) {alterna=1;}
            }
            if (alterna==1) {contalfa++;
            appoggio = rnd.nextInt(50);
            switch (appoggio) {
                case 0 : pass = pass+"A";break;
                case 1 : pass = pass+"B";break;
                case 2 : pass = pass+"C";break;
                case 3 : pass = pass+"D";break;
                case 4 : pass = pass+"E";break;
                case 5 : pass = pass+"F";break;
                case 6 : pass = pass+"G";break;
                case 7 : pass = pass+"H";break;
                case 8 : pass = pass+"I";break;
                case 9 : pass = pass+"J";break;
                case 10 : pass = pass+"K";break;
                case 11 : pass = pass+"L";break;
                case 12 : pass = pass+"M";break;
                case 13 : pass = pass+"N";break;
                case 14 : pass = pass+"O";break;
                case 15 : pass = pass+"P";break;
                case 16 : pass = pass+"Q";break;
                case 17 : pass = pass+"R";break;
                case 18 : pass = pass+"S";break;
                case 19 : pass = pass+"T";break;
                case 20 : pass = pass+"U";break;
                case 21 : pass = pass+"W";break;
                case 22 : pass = pass+"X";break;
                case 23 : pass = pass+"Y";break;
                case 24 : pass = pass+"Z";break;
                case 25 : pass = pass+"a";break;
                case 26 : pass = pass+"b";break;
                case 27 : pass = pass+"c";break;
                case 28 : pass = pass+"d";break;
                case 29 : pass = pass+"e";break;
                case 30 : pass = pass+"f";break;
                case 31 : pass = pass+"g";break;
                case 32 : pass = pass+"h";break;
                case 33 : pass = pass+"i";break;
                case 34 : pass = pass+"j";break;
                case 35 : pass = pass+"k";break;
                case 36 : pass = pass+"l";break;
                case 37 : pass = pass+"m";break;
                case 38 : pass = pass+"n";break;
                case 39 : pass = pass+"o";break;
                case 40 : pass = pass+"p";break;
                case 41 : pass = pass+"q";break;
                case 42 : pass = pass+"r";break;
                case 43 : pass = pass+"s";break;
                case 44 : pass = pass+"t";break;
                case 45 : pass = pass+"u";break;
                case 46 : pass = pass+"w";break;
                case 47 : pass = pass+"x";break;
                case 48 : pass = pass+"y";break;
                case 49 : pass = pass+"z";break;
            }
            }
            if (alterna==0) {contnum++;
            appoggio = rnd.nextInt(10);
            switch (appoggio) {
                case 0 : pass = pass+"0";break;
                case 1 : pass = pass+"1";break;
                case 2 : pass = pass+"2";break;
                case 3 : pass = pass+"3";break;
                case 4 : pass = pass+"4";break;
                case 5 : pass = pass+"5";break;
                case 6 : pass = pass+"6";break;
                case 7 : pass = pass+"7";break;
                case 8 : pass = pass+"8";break;
                case 9 : pass = pass+"9";break;
            }
            }
        }
        
        return pass;
    }
    
}