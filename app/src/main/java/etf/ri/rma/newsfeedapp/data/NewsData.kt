package etf.ri.rma.newsfeedapp.data

import etf.ri.rma.newsfeedapp.model.NewsItem

object NewsData {
    fun getAllNews(): List<NewsItem> {
        return listOf(
            NewsItem(
                id = "1",
                title = "Reportaža iz Prače",
                snippet = "Uspjeh Elvisine zadruge i Indirinog gazdinstva motivišu ljude na povratak: Puno se radi, ali komotno i slobodno živi",
                imageUrl = "drawable/image.jpg",
                category = "Sport",
                isFeatured = true,
                source = "Klix.ba",
                publishedDate = "2023-10-01"
            ),
            NewsItem(
                id = "2",
                title = "Hoće li položiti?",
                snippet = "\"Ko igra za raju, zanemaruje taktiku...\": Giorgia Meloni pred najvećim ispitom političke karijere",
                imageUrl = "drawable/image.jpg",
                category = "Politika",
                isFeatured = true,
                source = "Izvor 2",
                publishedDate = "2025-10-02"
            ),
            NewsItem(
                id = "3",
                title = "Potpuna zamjena teza",
                snippet = "Otac stradale u padu nadstrešnice na Vučićevom skupu iznio monstruozne tvrdnje: \"To je bio teroristički čin izvana\".",
                imageUrl = "drawable/image.jpg",
                category = "Politika",
                isFeatured = true,
                source = "Izvor 3",
                publishedDate = "2025-04-03"
            ),
            NewsItem(
                id = "4",
                title = "Kreirao preokret",
                snippet = "Ronaldo postigao najljepši gol od dolaska u Saudijsku Arabiju, pogledajte njegovu majstoriju",
                imageUrl = "drawable/image.jpg",
                category = "Sport",
                isFeatured = false,
                source = "Source 4",
                publishedDate = "2023-11-04"
            ),
            NewsItem(
                id = "5",
                title = "Pogledajte fotografije",
                snippet = "Pun mjesec zasjao je večeras iznad Sarajeva: Znate li zašto se naziva ružičastim punim mjesecom?",
                imageUrl = "drawable/image.jpg",
                category = "Nauka/tehnologija",
                isFeatured = true,
                source = "Source 5",
                publishedDate = "2023-10-05"
            ),
            NewsItem(
                id = "6",
                title = "Ministarski sastanak",
                snippet = "Konaković, Fidan i Grlić Radman u Antaliji: Razgovarali o saradnji, ali i potezima RS-a",
                imageUrl = "drawable/image.jpg",
                category = "Politika",
                isFeatured = false,
                source = "Source 6",
                publishedDate = "2023-10-06"
            ),
            NewsItem(
                id = "7",
                title = "Premiership Sport",
                snippet = "Neugodno prizemljenje za Arsenal, Liverpool nadomak titule: Topnici kiksali, Janelt igrao 70 minuta.",
                imageUrl = "drawable/image.jpg",
                category = "Sport",
                isFeatured = true,
                source = "Source 7",
                publishedDate = "2023-10-07"
            ),
            NewsItem(
                id = "8",
                title = "Prvenstvo i BiH",
                snippet = "Bosna deklasirala Sparse na Grbavici i jednom nogom ih gurnula u niži rang",
                imageUrl = "drawable/image.jpg",
                category = "Sport",
                isFeatured = false,
                source = "Source 8",
                publishedDate = "2023-10-08"
            ),
            NewsItem(
                id = "9",
                title = "Turnir mladih džudo nada",
                snippet = "Mladi džudisti potvrdili kvalitet, četiri zlata za BiH.",
                imageUrl = "drawable/image.jpg",
                category = "Sport",
                isFeatured = true,
                source = "Source 9",
                publishedDate = "2023-10-09"
            ),
            NewsItem(
                id = "10",
                title = "Za fanove Harry Pottera",
                snippet = "Hogwarts Legacy među besplatnim aprilskim igrama za PlayStation Plus",
                imageUrl = "drawable/image.jpg",
                category = "Nauka/tehnologija",
                isFeatured = false,
                source = "Source 10",
                publishedDate = "2023-10-10"
            ),
            NewsItem(
                id = "11",
                title = "Nevjerovatni prizori",
                snippet = "Astronaut s ISS-a snimio zadivljujući \"ples\" polarne svjetlosti iznad Zemlje",
                imageUrl = "drawable/image.jpg",
                category = "Nauka/tehnologija",
                isFeatured = true,
                source = "Source 11",
                publishedDate = "2023-10-11"
            ),
            NewsItem(
                id = "12",
                title = "Igrački povratak",
                snippet = "Wild Heart S od kraja jula na Nintendu Switch 2",
                imageUrl = "drawable/image.jpg",
                category = "Nauka/tehnologija",
                isFeatured = false,
                source = "Source 12",
                publishedDate = "2023-10-12"
            ),
            NewsItem(
                id = "13",
                title = "Potvrdila policija",
                snippet = "Lokaliziran požar na objektu firme u Živinicama, nastala velika materijalna šteta",
                imageUrl = "drawable/image.jpg",
                category = "Politika",
                isFeatured = false,
                source = "Source 13",
                publishedDate = "2023-10-13"
            ),
            NewsItem(
                id = "14",
                title = "Za 12.000 dolara",
                snippet = "Žena u SAD-u unajmila plaćenog ubicu preko Tindera da ubije bivšeg momka i njegovu kćerku",
                imageUrl = "drawable/image.jpg",
                category = "Politika",
                isFeatured = false,
                source = "Source 14",
                publishedDate = "2023-10-14"
            ),
            NewsItem(
                id = "15",
                title = "AMERIČKI PREDSJEDNIK",
                snippet = "Tramp šalje vojsku na granicu s Meksikom: Eskalira napetost s migrantima na jugu SAD.",
                imageUrl = "drawable/image.jpg",
                category = "Politika",
                isFeatured = true,
                source = "Source 15",
                publishedDate = "2023-10-15"
            ),
            NewsItem(
                id = "16",
                title = "RAZGOVORI I POZIVI",
                snippet = "Noviteti na WhatsAppu: Aplikacija sada ima gomilu novih funkcija",
                imageUrl = "drawable/image.jpg",
                category = "Nauka/tehnologija",
                isFeatured = false,
                source = "Source 16",
                publishedDate = "2023-10-16"
            ),
            NewsItem(
                id = "17",
                title = "NEOČEKIVANI NALAZI",
                snippet = "Naučnici nadrogirali ribe: Rezultati istraživanja ih jako iznenadili",
                imageUrl = "drawable/image.jpg",
                category = "Sport",
                isFeatured = false,
                source = "Source 17",
                publishedDate = "2023-10-17"
            ),
            NewsItem(
                id = "18",
                title = "KLIMATSKE PROMJENE",
                snippet = "Naučnici upozoravaju: Otopit će se svi austrijski ledenjaci",
                imageUrl = "drawable/image.jpg",
                category = "Nauka/tehnologija",
                isFeatured = false,
                source = "Source 18",
                publishedDate = "2023-10-18"
            ),
            NewsItem(
                id = "19",
                title = "EUROBASKET 2025",
                snippet = "Član slavne generacije BiH Emir Halimić za \"Avaz\": Dugo čekamo rušenje rezultata iz 1993.",
                imageUrl = "drawable/image.jpg",
                category = "Sport",
                isFeatured = true,
                source = "Source 19",
                publishedDate = "2023-10-19"
            ),
            NewsItem(
                id = "20",
                title = "RASPISANA POTJERNICA",
                snippet = "Kako je prešao granicu: Osim Dodika, u Beogradu danas i Nenad Stevandić.",
                imageUrl = "drawable/image.jpg",
                category = "Poliitika",
                isFeatured = false,
                source = "Source 20",
                publishedDate = "2023-10-20"
            )
        )
    }
}
