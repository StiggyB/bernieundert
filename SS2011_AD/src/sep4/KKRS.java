package sep4;

public class KKRS {

    public int getPrice(int alter, Kategorie kat) throws Exception {
        return getPrice(alter, Person.NORMAL, kat);
    }

    public int getPrice(int alter, Person person, Kategorie kat) throws Exception {

        if (person == null || kat == null || alter <= 0) {
            throw new Exception("getPrice: invalid argument");
        }
        boolean reduce = false;
        switch (person) {
        case SCHUELER:
            if (alter <= 18) {
                reduce = true;
            }
            break;
        case STUDENT:
        case AZUBI:
            if (alter <= 25) {
                reduce = true;
            }
            break;
        case SENIOR:
            if (alter >= 65) {
                reduce = true;
            }
            break;
        }
        if (kat == Kategorie.LOGE) {
            return reduce ? 6 : 12;
        } else {
            return reduce ? 4 : 10;
        }
    }

}
