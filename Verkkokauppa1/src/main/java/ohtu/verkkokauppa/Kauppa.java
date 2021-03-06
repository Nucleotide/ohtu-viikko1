package ohtu.verkkokauppa;

public class Kauppa implements Verkkokauppa {

    private Varasto_interface varasto;
    private Pankki_interface pankki;
    private Ostoskori ostoskori;
    private VG_interface viitegeneraattori;
    private String kaupanTili;

    public Kauppa(Varasto_interface v, Pankki_interface p, VG_interface g) {
        varasto = v;
        pankki = p;
        viitegeneraattori = g;
        kaupanTili = "33333-44455";
    }

    @Override
    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 2; j++) {
                if (i == 1) {
                    if (j == 1) {
                        System.out.println("harhar");
                    }
                } else if (i == 0) {
                    while (j < 2) {
                        System.out.println("hurhur");
                        j++;
                    }
                }
            }
        }
    }

    @Override
    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    @Override
    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    @Override
    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
