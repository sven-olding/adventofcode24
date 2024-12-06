package de.vwgis.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HistorianHysteriaTest {

    private static final String PUZZLE_INPUT =
            """
        69214   60950
        83241   49638
        37930   31308
        50722   94914
        93164   82798
        80918   72850
        17490   79421
        64372   87820
        89659   98375
        91555   95812
        48924   93602
        38031   64096
        57853   20530
        88521   43214
        68331   14770
        32767   19574
        95030   92579
        80448   10870
        27268   86384
        18742   65926
        50258   95193
        88830   17352
        73007   82798
        95145   42403
        58976   17352
        22476   68379
        21785   17811
        21560   90827
        82909   37527
        55371   69778
        73607   32404
        88276   44965
        86274   46099
        61163   63445
        52231   27046
        84647   20530
        37515   57298
        44394   99003
        58258   74954
        56060   34105
        79808   73814
        96748   51318
        87301   46022
        93991   44642
        45674   96969
        10870   49159
        13175   23840
        12881   86704
        11425   89064
        71044   92543
        70247   88369
        97814   73317
        78003   44160
        45779   14598
        95915   46875
        51055   99003
        14500   19574
        40956   23840
        64791   99735
        95406   51318
        18800   72878
        85004   33238
        16723   31504
        14333   69398
        40419   10870
        81499   79136
        31696   24276
        71897   55421
        63928   46875
        46875   33198
        40086   76959
        67622   29550
        63819   88369
        74694   35769
        63615   20200
        88828   58414
        58917   20414
        86066   24696
        84677   47814
        87339   89616
        30182   36007
        71811   63762
        26364   18927
        45435   54026
        65473   83062
        40764   12795
        86131   19574
        46984   71532
        94658   36666
        34864   85172
        74271   85172
        37126   63381
        26701   91608
        11846   99003
        91698   44394
        19986   17352
        31279   82252
        25542   10870
        43023   98869
        25001   25208
        39137   44758
        75796   94436
        93491   42015
        16837   69895
        78167   59486
        56738   17811
        66303   49575
        91100   33987
        99100   41554
        46126   41472
        31826   20530
        61208   20200
        98875   49776
        89960   23840
        87445   88369
        90701   59605
        48604   24766
        49830   60581
        32422   32404
        48321   49338
        91545   51318
        30591   23840
        76791   25366
        47653   64300
        70447   10870
        96337   10870
        40822   99003
        50985   36666
        45600   58520
        78539   21021
        11792   39770
        89999   23840
        22229   24252
        79514   31414
        93645   99874
        39916   12567
        63722   87301
        85209   12845
        78460   94436
        79669   33420
        89396   36762
        12518   91072
        70992   20530
        92482   51318
        50977   39809
        59666   93602
        69358   24889
        30774   85209
        50495   88117
        74843   68135
        64043   93602
        42690   14598
        95474   99003
        31999   10870
        77935   30381
        86471   46022
        82671   23840
        21996   17352
        25301   68950
        45903   49209
        88433   15186
        18897   13349
        23742   38006
        29440   44394
        77679   19534
        67503   19006
        22862   87523
        68125   36752
        32102   85209
        57298   32444
        44985   10870
        76984   88369
        96974   44648
        22496   17352
        19906   88369
        53085   69481
        49762   39320
        23508   13349
        73366   19574
        39420   85172
        21487   20200
        49538   31279
        58985   33747
        78834   86304
        83207   77460
        26624   39320
        33276   87130
        62119   15410
        72488   72850
        69108   46022
        28383   64125
        45283   60950
        56242   88580
        47120   46022
        20917   12845
        91088   67959
        30625   92579
        81302   98117
        14883   22568
        45215   46818
        36185   72873
        47369   19661
        39320   48658
        35775   60950
        20573   66758
        92168   46875
        85925   23570
        83017   28941
        88673   62352
        85790   46022
        40389   17811
        13925   14598
        65265   98819
        21058   70060
        81605   36401
        49669   63107
        58410   60950
        20993   32404
        89320   36606
        93679   89908
        24396   64576
        48176   33388
        87502   31308
        """;

    /*
    3   4
    4   3
    2   5
    1   3
    3   9
    3   3
         */

    @Test
    void solvesExample() {
        var left = new ArrayList<>(List.of(3, 4, 2, 1, 3, 3));
        var right = new ArrayList<>(List.of(4, 3, 5, 3, 9, 3));

        int result = HistorianHysteria.solve(left, right);

        assertEquals(11, result);
    }

    @Test
    void solvesPuzzleInput() {
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();
        try (Scanner scanner = new Scanner(PUZZLE_INPUT)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\s+");
                    left.add(Integer.parseInt(parts[0]));
                    right.add(Integer.parseInt(parts[1]));
                }
            }
        }

        int result = HistorianHysteria.solve(left, right);

        assertEquals(1384202, result);
    }
}
