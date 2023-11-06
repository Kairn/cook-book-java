package io.esoma.cbj.algo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class ThreeWayStringSortTest {

    @Test
    void testSort1() {
        String[] unsorted = new String[] {"a", "b", "c", "d", "e", "f", "g", "h"};
        String[] sorted = new String[] {"a", "b", "c", "d", "e", "f", "g", "h"};
        String[] result = ThreeWayStringSort.sort(unsorted);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, result);
    }

    @Test
    void testSort2() {
        String[] unsorted = new String[] {
            "aaaaaaae", "aaaaae", "aaaaaaaaaa4", "aacbsaaaaa", "aaaaaaaaa1", "aaaaaaaaaaa_", "aacxas312", "a123"
        };
        String[] sorted = new String[] {
            "a123", "aaaaaaaaa1", "aaaaaaaaaa4", "aaaaaaaaaaa_", "aaaaaaae", "aaaaae", "aacbsaaaaa", "aacxas312"
        };
        String[] result = ThreeWayStringSort.sort(unsorted);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, result);
    }

    @Test
    void testSort3() {
        String[] unsorted = new String[] {
            "473S", "4mge", "Uyer", "yCi5", "lwoi", "jEWy", "nxB5", "ZJpq", "tkPN", "A8Rf",
            "QrbQ", "CCuK", "Obaj", "bT0v", "DCvV", "23m2", "YEbZ", "CoVj", "IMlZ", "HUjp"
        };
        String[] sorted = new String[] {
            "23m2", "473S", "4mge", "A8Rf", "bT0v", "CCuK", "CoVj", "DCvV", "HUjp", "IMlZ",
            "jEWy", "lwoi", "nxB5", "Obaj", "QrbQ", "tkPN", "Uyer", "yCi5", "YEbZ", "ZJpq"
        };
        // Fix case sensitivity.
        Arrays.sort(sorted);
        String[] result = ThreeWayStringSort.sort(unsorted);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, result);
    }

    @Test
    void testSort4() {
        String[] unsorted = new String[] {
            "BknTzIPa",
            "DtdmrTEIOtPUlJLCOTp",
            "ClFqtrY",
            "jqgk",
            "VAx",
            "AzzMW",
            "mUfxtgTyJLv",
            "GZDgt",
            "Tpek",
            "vSn",
            "wFCnI",
            "moPprGUG",
            "btnXovHdOCwdUrDflcWXnESNqwWoKHaiMlEKvGKsYWewwFdjfgLGOldDBkmW",
            "TqKDo",
            "jjeuHvpRXEpdamb",
            "ZcXydoyCMczEjfjqHmthmC",
            "DdYkFfpUHUxaWCTcSLvidMWR",
            "UHUB",
            "ybaoHXhNZtoiiGO",
            "VKmfZw",
            "GIss",
            "gDqBuumSFAsaDKTuTVodzoHGsAULaDuRZdVSdIvYnFtrAPzNKJbEyehSFLdqcIc",
            "rwZHzgoe",
            "ZJqJnCEr",
            "wJRIDeHJqMwhKPAsCdKGonA",
            "NynzanAwZJLzceXsybpezIBdZDyOOWcAufifTDYpuyqeCsNPYGCwGdcUmv",
            "IwSTLNpSUs",
            "EyCZAjIry",
            "MBTXYNAnaJuBMcJSFDohflTqaImrtdADPKdbWjqpRcssSDaeHtmpFowpyGqmSaNeLURpjGerETGS",
            "sWyVJihxCbQkgQZsgOYDNeGqmxueExz",
            "FCNgRwAfulqDCBdG",
            "riHpgofSxpioxXcyStTJpSJssqrnWqrVzQPFEcDpFNvGvMEekKAshxEWKUzaFOPIowboTa",
            "muccbCUgPeXsLtpoEkcZQCHArGzehLdKaU",
            "UCDJjpZAiHmSRTWJkaQzbpWAGZQKHQbWKLYwuqiHnZqAJUvtGmpbKGQfAo",
            "dcWANechyovzvfxhowQHSEwIqliwHRXUO",
            "BUe",
            "ptMGKGwChLrGAfVxUhYviBUIIBoM",
            "XCRagCVcuCEcURDAoFMnzQzx",
            "NZWtUpLF",
            "aikFH",
            "RPFUIeHlPktYUbxa",
            "oiUXvey",
            "JMkdTHeGWlRCocwLWpvl",
            "OtbphoGECgHcSsC",
            "HoeBZQMjmHAAlKNGEPkwcqlsM",
            "ztRTDPTlGOoaXmwtNiMwpT",
            "KamUeHJGgiPpcBXcPCrKyXpkScvL",
            "ZD",
            "iFPnLLvVdnH",
            "bOMGJtw",
            "hUUdahopH",
            "ZFdBWGkeFPwVyfyoG",
            "mHsxNRnCugZqjEegVDcbrNno",
            "sYCMwFNXpIwtePiwhpVTkhj",
            "xRumiDAgEmuhIyAYPSgfNS",
            "dvRBpzq",
            "WSWsgstawBBFllRBQxFhKkYgs",
            "viPfQZAlKyKreTAUhbGuYtmMDqAhZLllQYEnNkIYhUTYXnK",
            "XhIMHYLiIZrLRWErXFHnryQCrq",
            "kIuuaeNy",
            "fiCMgNIRPeDeHkm",
            "hFCtgxybXEW",
            "tMzsmqMPZblpE",
            "OD",
            "Rx",
            "PDZ",
            "ixja",
            "TNjTPpbKoLOqdstiTaqWmHFgeYdYAPvSTiSyorEyWWs",
            "TsDaSRTdytkxpkxjOoO",
            "nBMGcTc",
            "ET",
            "Nhs"
        };
        String[] sorted = new String[] {
            "aikFH",
            "AzzMW",
            "BknTzIPa",
            "bOMGJtw",
            "btnXovHdOCwdUrDflcWXnESNqwWoKHaiMlEKvGKsYWewwFdjfgLGOldDBkmW",
            "BUe",
            "ClFqtrY",
            "dcWANechyovzvfxhowQHSEwIqliwHRXUO",
            "DdYkFfpUHUxaWCTcSLvidMWR",
            "DtdmrTEIOtPUlJLCOTp",
            "dvRBpzq",
            "ET",
            "EyCZAjIry",
            "FCNgRwAfulqDCBdG",
            "fiCMgNIRPeDeHkm",
            "gDqBuumSFAsaDKTuTVodzoHGsAULaDuRZdVSdIvYnFtrAPzNKJbEyehSFLdqcIc",
            "GIss",
            "GZDgt",
            "hFCtgxybXEW",
            "HoeBZQMjmHAAlKNGEPkwcqlsM",
            "hUUdahopH",
            "iFPnLLvVdnH",
            "IwSTLNpSUs",
            "ixja",
            "jjeuHvpRXEpdamb",
            "JMkdTHeGWlRCocwLWpvl",
            "jqgk",
            "KamUeHJGgiPpcBXcPCrKyXpkScvL",
            "kIuuaeNy",
            "MBTXYNAnaJuBMcJSFDohflTqaImrtdADPKdbWjqpRcssSDaeHtmpFowpyGqmSaNeLURpjGerETGS",
            "mHsxNRnCugZqjEegVDcbrNno",
            "moPprGUG",
            "muccbCUgPeXsLtpoEkcZQCHArGzehLdKaU",
            "mUfxtgTyJLv",
            "nBMGcTc",
            "Nhs",
            "NynzanAwZJLzceXsybpezIBdZDyOOWcAufifTDYpuyqeCsNPYGCwGdcUmv",
            "NZWtUpLF",
            "OD",
            "oiUXvey",
            "OtbphoGECgHcSsC",
            "PDZ",
            "ptMGKGwChLrGAfVxUhYviBUIIBoM",
            "riHpgofSxpioxXcyStTJpSJssqrnWqrVzQPFEcDpFNvGvMEekKAshxEWKUzaFOPIowboTa",
            "RPFUIeHlPktYUbxa",
            "rwZHzgoe",
            "Rx",
            "sWyVJihxCbQkgQZsgOYDNeGqmxueExz",
            "sYCMwFNXpIwtePiwhpVTkhj",
            "tMzsmqMPZblpE",
            "TNjTPpbKoLOqdstiTaqWmHFgeYdYAPvSTiSyorEyWWs",
            "Tpek",
            "TqKDo",
            "TsDaSRTdytkxpkxjOoO",
            "UCDJjpZAiHmSRTWJkaQzbpWAGZQKHQbWKLYwuqiHnZqAJUvtGmpbKGQfAo",
            "UHUB",
            "VAx",
            "viPfQZAlKyKreTAUhbGuYtmMDqAhZLllQYEnNkIYhUTYXnK",
            "VKmfZw",
            "vSn",
            "wFCnI",
            "wJRIDeHJqMwhKPAsCdKGonA",
            "WSWsgstawBBFllRBQxFhKkYgs",
            "XCRagCVcuCEcURDAoFMnzQzx",
            "XhIMHYLiIZrLRWErXFHnryQCrq",
            "xRumiDAgEmuhIyAYPSgfNS",
            "ybaoHXhNZtoiiGO",
            "ZcXydoyCMczEjfjqHmthmC",
            "ZD",
            "ZFdBWGkeFPwVyfyoG",
            "ZJqJnCEr",
            "ztRTDPTlGOoaXmwtNiMwpT"
        };
        // Fix case sensitivity.
        Arrays.sort(sorted);
        String[] result = ThreeWayStringSort.sort(unsorted);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, result);
    }

    @Test
    void testSort5() {
        String[] unsorted = new String[] {
            "Lorem",
            "ipsum",
            "dolor",
            "sit",
            "amet,",
            "consectetur",
            "adipiscing",
            "elit",
            "sed",
            "do",
            "eiusmod",
            "tempor",
            "incididunt",
            "ut",
            "labore",
            "et",
            "dolore",
            "magna",
            "aliqua",
            "Ut",
            "enim",
            "ad",
            "minim",
            "veniam,",
            "quis",
            "nostrud",
            "exercitation",
            "ullamco",
            "laboris",
            "nisi",
            "ut",
            "aliquip",
            "ex",
            "ea",
            "commodo",
            "consequat.",
            "Duis",
            "aute",
            "irure",
            "dolor",
            "in",
            "reprehenderit",
            "in",
            "voluptate",
            "velit",
            "esse",
            "cillum",
            "dolore",
            "eu",
            "fugiat",
            "nulla",
            "pariatur.",
            "Excepteur",
            "sint",
            "occaecat",
            "cupidatat",
            "non",
            "proident,",
            "sunt",
            "in",
            "culpa",
            "qui",
            "officia",
            "deserunt",
            "mollit",
            "anim",
            "id",
            "est",
            "laborum."
        };
        String[] sorted = new String[] {
            "ad",
            "adipiscing",
            "aliqua",
            "aliquip",
            "amet,",
            "anim",
            "aute",
            "cillum",
            "commodo",
            "consectetur",
            "consequat.",
            "culpa",
            "cupidatat",
            "deserunt",
            "do",
            "dolor",
            "dolor",
            "dolore",
            "dolore",
            "Duis",
            "ea",
            "eiusmod",
            "elit",
            "enim",
            "esse",
            "est",
            "et",
            "eu",
            "ex",
            "Excepteur",
            "exercitation",
            "fugiat",
            "id",
            "in",
            "in",
            "in",
            "incididunt",
            "ipsum",
            "irure",
            "labore",
            "laboris",
            "laborum.",
            "Lorem",
            "magna",
            "minim",
            "mollit",
            "nisi",
            "non",
            "nostrud",
            "nulla",
            "occaecat",
            "officia",
            "pariatur.",
            "proident,",
            "qui",
            "quis",
            "reprehenderit",
            "sed",
            "sint",
            "sit",
            "sunt",
            "tempor",
            "ullamco",
            "ut",
            "ut",
            "Ut",
            "velit",
            "veniam,",
            "voluptate"
        };
        // Fix case sensitivity.
        Arrays.sort(sorted);
        String[] result = ThreeWayStringSort.sort(unsorted);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, result);
    }

    @Test
    void testSort6() {
        String[] unsorted = new String[] {
            "CAAAGACT",
            "CGCACGGA",
            "TAATAAAC",
            "GTCAAGCG",
            "CGCGCCCA",
            "GCTGATGC",
            "CAGATACG",
            "CCTTTCAT",
            "GTCCGTTG",
            "CCGACTCG",
            "TACATAAA",
            "CTTCATCG",
            "GGGTACTA",
            "TGCAGTCA",
            "CTGGGTGG",
            "TAGATATG",
            "CTGAAGCC",
            "GAACGACT",
            "TGAGCATG",
            "ACAACTAC",
            "ACAGATCT",
            "TAATGGGC",
            "ACTCGAAA",
            "AGGCCGTG",
            "CGTTAGTG",
            "GTTGGTGA",
            "AAGGTCAC",
            "GAACATGG",
            "TCGACCCG",
            "GATAAAGA",
            "GCTACGTC",
            "GTGAGAGC",
            "GCTTGGGA",
            "TTTGTACA",
            "CGACAGAG",
            "GAAAGCGT",
            "GGTGAATC",
            "TTATTAAA",
            "GTACATGG",
            "CTGGACTT",
            "GGTGAAAC",
            "CCGCTATG",
            "CTCATGAC",
            "AACAACGT",
            "CTCGCAAC",
            "AATCACCC",
            "TCTTTGGA",
            "GAGTAGAC",
            "ATTAACTC",
            "ACGCGAGA",
            "ATTTTCTT",
            "GGAGAGGG",
            "GGTGGTCC",
            "CCGCCCGA",
            "GTTGGGTG",
            "GGTTTCAT",
            "GGACGACC",
            "TGTCTCAA",
            "GTATTTCA",
            "GTCTCACA",
            "CATCAAAT",
            "AACAGCTT",
            "GACGCTTA",
            "CAGCTAAT",
            "CTGCGGCA",
            "GCCACATT",
            "GATTGCCG",
            "CTCGGTGA",
            "CGAATCTA",
            "AGTCTAGC",
            "TCGGAGTA",
            "CTAATTCC",
            "TGCGTGTG",
            "TAGACTAT",
            "ACATTCTC",
            "TGGACGTC",
            "GAGAGGGA",
            "CCTGCGCG",
            "TAACGTAG",
            "ATTGCCGT",
            "AGATCGGA",
            "TATGTCGT",
            "AGATTGAG",
            "GTATCGCG",
            "AGGTCCGC",
            "AAATAGAG",
            "TCCGTAAC",
            "CCATTTAA",
            "CGTTGAAC",
            "TTCTTGAA",
            "CGCGGCGC",
            "AGAGAGTT",
            "GTTTGCCG",
            "GTCTCAGG",
            "AGCCCTTA",
            "GAGTTCTA",
            "AGGCTGAA",
            "ACTGCTAG",
            "GGCACCTC",
            "TGCCCGAC"
        };
        String[] sorted = new String[] {
            "AAATAGAG",
            "AACAACGT",
            "AACAGCTT",
            "AAGGTCAC",
            "AATCACCC",
            "ACAACTAC",
            "ACAGATCT",
            "ACATTCTC",
            "ACGCGAGA",
            "ACTCGAAA",
            "ACTGCTAG",
            "AGAGAGTT",
            "AGATCGGA",
            "AGATTGAG",
            "AGCCCTTA",
            "AGGCCGTG",
            "AGGCTGAA",
            "AGGTCCGC",
            "AGTCTAGC",
            "ATTAACTC",
            "ATTGCCGT",
            "ATTTTCTT",
            "CAAAGACT",
            "CAGATACG",
            "CAGCTAAT",
            "CATCAAAT",
            "CCATTTAA",
            "CCGACTCG",
            "CCGCCCGA",
            "CCGCTATG",
            "CCTGCGCG",
            "CCTTTCAT",
            "CGAATCTA",
            "CGACAGAG",
            "CGCACGGA",
            "CGCGCCCA",
            "CGCGGCGC",
            "CGTTAGTG",
            "CGTTGAAC",
            "CTAATTCC",
            "CTCATGAC",
            "CTCGCAAC",
            "CTCGGTGA",
            "CTGAAGCC",
            "CTGCGGCA",
            "CTGGACTT",
            "CTGGGTGG",
            "CTTCATCG",
            "GAAAGCGT",
            "GAACATGG",
            "GAACGACT",
            "GACGCTTA",
            "GAGAGGGA",
            "GAGTAGAC",
            "GAGTTCTA",
            "GATAAAGA",
            "GATTGCCG",
            "GCCACATT",
            "GCTACGTC",
            "GCTGATGC",
            "GCTTGGGA",
            "GGACGACC",
            "GGAGAGGG",
            "GGCACCTC",
            "GGGTACTA",
            "GGTGAAAC",
            "GGTGAATC",
            "GGTGGTCC",
            "GGTTTCAT",
            "GTACATGG",
            "GTATCGCG",
            "GTATTTCA",
            "GTCAAGCG",
            "GTCCGTTG",
            "GTCTCACA",
            "GTCTCAGG",
            "GTGAGAGC",
            "GTTGGGTG",
            "GTTGGTGA",
            "GTTTGCCG",
            "TAACGTAG",
            "TAATAAAC",
            "TAATGGGC",
            "TACATAAA",
            "TAGACTAT",
            "TAGATATG",
            "TATGTCGT",
            "TCCGTAAC",
            "TCGACCCG",
            "TCGGAGTA",
            "TCTTTGGA",
            "TGAGCATG",
            "TGCAGTCA",
            "TGCCCGAC",
            "TGCGTGTG",
            "TGGACGTC",
            "TGTCTCAA",
            "TTATTAAA",
            "TTCTTGAA",
            "TTTGTACA"
        };
        String[] result = ThreeWayStringSort.sort(unsorted);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, result);
    }

    @Test
    void testSort7() {
        String[] unsorted = new String[] {
            "4919",
            "1",
            "40",
            "4839",
            "06",
            "8104",
            "4057",
            "82085",
            "8",
            "2",
            "9",
            "743190",
            "4",
            "1310647",
            "8582413",
            "24",
            "32676",
            "84620",
            "7",
            "5",
            "6",
            "30643403",
            "621",
            "258",
            "819",
            "65",
            "99",
            "42392",
            "078",
            "3",
            "60",
            "466",
            "90",
            "291",
            "37",
            "87",
            "63763249",
            "84",
            "67699731",
            "6745",
            "2",
            "2",
            "750",
            "2",
            "0",
            "6",
            "8609",
            "66902",
            "1615",
            "392",
            "8",
            "950",
            "62",
            "8",
            "470872",
            "16111",
            "6957761",
            "79968",
            "2391566",
            "90",
            "257",
            "7930",
            "66631",
            "084907",
            "9183261",
            "4593112300",
            "4894",
            "3105",
            "56",
            "4583754734",
            "492969134517552",
            "2640719674",
            "06",
            "350",
            "151",
            "414",
            "55422",
            "6",
            "89",
            "02245",
            "4",
            "25",
            "8430",
            "226",
            "83"
        };
        String[] sorted = new String[] {
            "0",
            "02245",
            "06",
            "06",
            "078",
            "084907",
            "1",
            "1310647",
            "151",
            "16111",
            "1615",
            "2",
            "2",
            "2",
            "2",
            "226",
            "2391566",
            "24",
            "25",
            "257",
            "258",
            "2640719674",
            "291",
            "3",
            "30643403",
            "3105",
            "32676",
            "350",
            "37",
            "392",
            "4",
            "4",
            "40",
            "4057",
            "414",
            "42392",
            "4583754734",
            "4593112300",
            "466",
            "470872",
            "4839",
            "4894",
            "4919",
            "492969134517552",
            "5",
            "55422",
            "56",
            "6",
            "6",
            "6",
            "60",
            "62",
            "621",
            "63763249",
            "65",
            "66631",
            "66902",
            "6745",
            "67699731",
            "6957761",
            "7",
            "743190",
            "750",
            "7930",
            "79968",
            "8",
            "8",
            "8",
            "8104",
            "819",
            "82085",
            "83",
            "84",
            "8430",
            "84620",
            "8582413",
            "8609",
            "87",
            "89",
            "9",
            "90",
            "90",
            "9183261",
            "950",
            "99"
        };
        String[] result = ThreeWayStringSort.sort(unsorted);
        Logger.debug(Arrays.toString(unsorted));
        assertArrayEquals(sorted, result);
    }
}
