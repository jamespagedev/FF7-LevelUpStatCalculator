/**
 * Created by James Page on 5/20/2017.
 */
package ClassFiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TestCalculateRangeLevels extends CalculateRangeLevels {

    //******************************************************************************************************************
    //******************************************************* HP *******************************************************
    //******************************************************************************************************************
    public static boolean testHpCloudValuesLvlsSixToSixteenAndEightyNineToNinetyNinePathsToEndLevelMaxValue(){
        ArrayList<ArrayList<Integer>> expectedResults = new ArrayList<>();
        expectedResults.add(new ArrayList<>(Collections.singletonList(314))); // lvl 6
        expectedResults.add(new ArrayList<>(Arrays.asList(323, 325, 327, 329, 331, 333, 334)));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                336, 338, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                351, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367,
                368, 369, 370, 371, 372)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383,
                384, 385, 386, 387, 388, 389, 390, 391)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400,
                401, 402, 403, 404, 405, 406, 407, 408, 409, 410)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450,
                451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466,
                467, 468, 469, 470, 471, 472)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490,
                491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506,
                507, 508, 509, 510, 511, 512, 513)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530,
                531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546,
                547, 548, 549, 550, 551, 552, 553, 554)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569,
                570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585,
                586, 587, 588, 589, 590, 591, 592, 593, 594, 595)));

        expectedResults.add(new ArrayList<>(Arrays.asList( // level 16
                594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609,
                610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625,
                626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637)));

        expectedResults.add(new ArrayList<>(Arrays.asList( // level 89
                8431, 8441, 8450, 8459, 8460, 8468, 8469, 8470, 8477, 8478, 8479, 8480, 8486, 8487,
                8488, 8489, 8490, 8495, 8496, 8497, 8498, 8499, 8504, 8505, 8506, 8507, 8508, 8509,
                8513, 8514, 8515, 8516, 8517, 8518, 8519, 8522, 8523, 8524, 8525, 8526, 8527, 8528,
                8529, 8531)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                8548, 8557, 8566, 8567, 8575, 8576, 8577, 8584, 8585, 8586, 8587, 8593, 8594, 8595,
                8596, 8597, 8602, 8603, 8604, 8605, 8606, 8611, 8612, 8613, 8614, 8615, 8616, 8620,
                8621, 8622, 8623, 8624, 8625, 8626, 8629)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                8655, 8664, 8673, 8674, 8682, 8683, 8684, 8691, 8692, 8693, 8694, 8700, 8701, 8702,
                8703, 8704, 8709, 8710, 8711, 8712, 8713, 8718, 8719, 8720, 8721, 8722, 8723, 8727)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                8762, 8771, 8780, 8781, 8789, 8790, 8791, 8798, 8799, 8800, 8801, 8807, 8808, 8809,
                8810, 8811, 8816, 8817, 8818, 8819, 8820, 8825)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                8869, 8878, 8887, 8888, 8896, 8897, 8898, 8905, 8906, 8907, 8908, 8914, 8915, 8916,
                8917, 8918, 8923)));

        expectedResults.add(new ArrayList<>(Arrays.asList(
                8976, 8985, 8994, 8995, 9003, 9004, 9005, 9012, 9013, 9014, 9015, 9021)));

        expectedResults.add(new ArrayList<>(Arrays.asList(9083, 9092, 9101, 9102, 9110, 9111, 9112, 9119)));
        expectedResults.add(new ArrayList<>(Arrays.asList(9190, 9199, 9208, 9209, 9217)));
        expectedResults.add(new ArrayList<>(Arrays.asList(9297, 9306, 9315)));
        expectedResults.add(new ArrayList<>(Arrays.asList(9404, 9413)));
        expectedResults.add(new ArrayList<>(Collections.singletonList(9511)));

        Characters cloud = new Characters("Cloud");
        int endLevel = 99;
        int endLevelDesiredHp = 0;
        ArrayList<ArrayList<Integer>> hpValues = calculateRangeLevelsHpResults(cloud.getCharacterName(), cloud.getLevel(), endLevel, cloud.getBaseHp(), endLevelDesiredHp);

        int level = cloud.getLevel(); // 6
        int expectedLevelIndex = 0;
        int expectedResultsIndex = 0;
        for (ArrayList<Integer> levelValues: hpValues){
            for (Integer hpValue: levelValues){
                if ((level >= cloud.getLevel() && level < 17) || (level >= 89 && level < 100)) {
                    if (!hpValue.equals(expectedResults.get(expectedLevelIndex).get(expectedResultsIndex))) {
                        System.out.println("Error: " + hpValue + " not " + expectedResults.get(expectedLevelIndex).get(expectedLevelIndex));
                        System.out.println("testHpCloudValuesLvlsSixToSixteenAndEightyNineToNinetyNinePathsToEndLevelMaxValue :: Failed");
                        return false;
                    }
                }
                expectedResultsIndex++;
            }
            expectedResultsIndex = 0;
            if ((level >= cloud.getLevel() && level < 17) || (level >= 89 && level < 100)){
                expectedLevelIndex++;
            }
            level++;
        }
        System.out.println("testHpCloudValuesLvlsSixToSixteenAndEightyNineToNinetyNinePathsToEndLevelMaxValue :: Passed");
        return true;
    }

    public static boolean testHpCloudFirstTwoLevelUpsAndLastTwoLevelUpsAllPossibleValues(){
        ArrayList<ArrayList<Integer>> expectedResults = new ArrayList<>();
        expectedResults.add(new ArrayList<>(Arrays.asList(323, 325, 327, 329, 331, 333, 334))); // lvl 7

        expectedResults.add(new ArrayList<>(Arrays.asList( // lvl 8
                336, 338, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353)));

        expectedResults.add(new ArrayList<>(Arrays.asList( // lvl 98
                8868, 8869, 8870, 8871, 8872, 8873, 8874, 8875, 8876, 8877, 8878, 8879, 8880, 8881, 8882, 8883, 8884,
                8885, 8886, 8887, 8888, 8889, 8890, 8891, 8892, 8893, 8894, 8895, 8896, 8897, 8898, 8899, 8900, 8901,
                8902, 8903, 8904, 8905, 8906, 8907, 8908, 8909, 8910, 8911, 8912, 8913, 8914, 8915, 8916, 8917, 8918,
                8919, 8920, 8921, 8922, 8923, 8924, 8925, 8926, 8927, 8928, 8929, 8930, 8931, 8932, 8933, 8934, 8935,
                8936, 8937, 8938, 8939, 8940, 8941, 8942, 8943, 8944, 8945, 8946, 8947, 8948, 8949, 8950, 8951, 8952,
                8953, 8954, 8955, 8956, 8957, 8958, 8959, 8960, 8961, 8962, 8963, 8964, 8965, 8966, 8967, 8968, 8969,
                8970, 8971, 8972, 8973, 8974, 8975, 8976, 8977, 8978, 8979, 8980, 8981, 8982, 8983, 8984, 8985, 8986,
                8987, 8988, 8989, 8990, 8991, 8992, 8993, 8994, 8995, 8996, 8997, 8998, 8999, 9000, 9001, 9002, 9003,
                9004, 9005, 9006, 9007, 9008, 9009, 9010, 9011, 9012, 9013, 9014, 9015, 9016, 9017, 9018, 9019, 9020,
                9021, 9022, 9023, 9024, 9025, 9026, 9027, 9028, 9029, 9030, 9031, 9032, 9033, 9034, 9035, 9036, 9037,
                9038, 9039, 9040, 9041, 9042, 9043, 9044, 9045, 9046, 9047, 9048, 9049, 9050, 9051, 9052, 9053, 9054,
                9055, 9056, 9057, 9058, 9059, 9060, 9061, 9062, 9063, 9064, 9065, 9066, 9067, 9068, 9069, 9070, 9071,
                9072, 9073, 9074, 9075, 9076, 9077, 9078, 9079, 9080, 9081, 9082, 9083, 9084, 9085, 9086, 9087, 9088,
                9089, 9090, 9091, 9092, 9093, 9094, 9095, 9096, 9097, 9098, 9099, 9100, 9101, 9102, 9103, 9104, 9105,
                9106, 9107, 9108, 9109, 9110, 9111, 9112, 9113, 9114, 9115, 9116, 9117, 9118, 9119, 9120, 9121, 9122,
                9123, 9124, 9125, 9126, 9127, 9128, 9129, 9130, 9131, 9132, 9133, 9134, 9135, 9136, 9137, 9138, 9139,
                9140, 9141, 9142, 9143, 9144, 9145, 9146, 9147, 9148, 9149, 9150, 9151, 9152, 9153, 9154, 9155, 9156,
                9157, 9158, 9159, 9160, 9161, 9162, 9163, 9164, 9165, 9166, 9167, 9168, 9169, 9170, 9171, 9172, 9173,
                9174, 9175, 9176, 9177, 9178, 9179, 9180, 9181, 9182, 9183, 9184, 9185, 9186, 9187, 9188, 9189, 9190,
                9191, 9192, 9193, 9194, 9195, 9196, 9197, 9198, 9199, 9200, 9201, 9202, 9203, 9204, 9205, 9206, 9207,
                9208, 9209, 9210, 9211, 9212, 9213, 9214, 9215, 9216, 9217, 9218, 9219, 9220, 9221, 9222, 9223, 9224,
                9225, 9226, 9227, 9228, 9229, 9230, 9231, 9232, 9233, 9234, 9235, 9236, 9237, 9238, 9239, 9240, 9241,
                9242, 9243, 9244, 9245, 9246, 9247, 9248, 9249, 9250, 9251, 9252, 9253, 9254, 9255, 9256, 9257, 9258,
                9259, 9260, 9261, 9262, 9263, 9264, 9265, 9266, 9267, 9268, 9269, 9270, 9271, 9272, 9273, 9274, 9275,
                9276, 9277, 9278, 9279, 9280, 9281, 9282, 9283, 9284, 9285, 9286, 9287, 9288, 9289, 9290, 9291, 9292,
                9293, 9294, 9295, 9296, 9297, 9298, 9299, 9300, 9301, 9302, 9303, 9304, 9305, 9306, 9307, 9308, 9309,
                9310, 9311, 9312, 9313, 9314, 9315, 9316, 9317, 9318, 9319, 9320, 9321, 9322, 9323, 9324, 9325, 9326,
                9327, 9328, 9329, 9330, 9331, 9332, 9333, 9334, 9335, 9336, 9337, 9338, 9339, 9340, 9341, 9342, 9343,
                9344, 9345, 9346, 9347, 9348, 9349, 9350, 9351, 9352, 9353, 9354, 9355, 9356, 9357, 9358, 9359, 9360,
                9361, 9362, 9363, 9364, 9365, 9366, 9367, 9368, 9369, 9370, 9371, 9372, 9373, 9374, 9375, 9376, 9377,
                9378, 9379, 9380, 9381, 9382, 9383, 9384, 9385, 9386, 9387, 9388, 9389, 9390, 9391, 9392, 9393, 9394,
                9395, 9396, 9397, 9398, 9399, 9400, 9401, 9402, 9403, 9404, 9405, 9406, 9407, 9408, 9409, 9410, 9411,
                9412, 9413)));

        expectedResults.add(new ArrayList<>(Arrays.asList( // lvl 99
                8960, 8961, 8962, 8963, 8964, 8965, 8966, 8967, 8968, 8969, 8970, 8971, 8972, 8973, 8974, 8975, 8976,
                8977, 8978, 8979, 8980, 8981, 8982, 8983, 8984, 8985, 8986, 8987, 8988, 8989, 8990, 8991, 8992, 8993,
                8994, 8995, 8996, 8997, 8998, 8999, 9000, 9001, 9002, 9003, 9004, 9005, 9006, 9007, 9008, 9009, 9010,
                9011, 9012, 9013, 9014, 9015, 9016, 9017, 9018, 9019, 9020, 9021, 9022, 9023, 9024, 9025, 9026, 9027,
                9028, 9029, 9030, 9031, 9032, 9033, 9034, 9035, 9036, 9037, 9038, 9039, 9040, 9041, 9042, 9043, 9044,
                9045, 9046, 9047, 9048, 9049, 9050, 9051, 9052, 9053, 9054, 9055, 9056, 9057, 9058, 9059, 9060, 9061,
                9062, 9063, 9064, 9065, 9066, 9067, 9068, 9069, 9070, 9071, 9072, 9073, 9074, 9075, 9076, 9077, 9078,
                9079, 9080, 9081, 9082, 9083, 9084, 9085, 9086, 9087, 9088, 9089, 9090, 9091, 9092, 9093, 9094, 9095,
                9096, 9097, 9098, 9099, 9100, 9101, 9102, 9103, 9104, 9105, 9106, 9107, 9108, 9109, 9110, 9111, 9112,
                9113, 9114, 9115, 9116, 9117, 9118, 9119, 9120, 9121, 9122, 9123, 9124, 9125, 9126, 9127, 9128, 9129,
                9130, 9131, 9132, 9133, 9134, 9135, 9136, 9137, 9138, 9139, 9140, 9141, 9142, 9143, 9144, 9145, 9146,
                9147, 9148, 9149, 9150, 9151, 9152, 9153, 9154, 9155, 9156, 9157, 9158, 9159, 9160, 9161, 9162, 9163,
                9164, 9165, 9166, 9167, 9168, 9169, 9170, 9171, 9172, 9173, 9174, 9175, 9176, 9177, 9178, 9179, 9180,
                9181, 9182, 9183, 9184, 9185, 9186, 9187, 9188, 9189, 9190, 9191, 9192, 9193, 9194, 9195, 9196, 9197,
                9198, 9199, 9200, 9201, 9202, 9203, 9204, 9205, 9206, 9207, 9208, 9209, 9210, 9211, 9212, 9213, 9214,
                9215, 9216, 9217, 9218, 9219, 9220, 9221, 9222, 9223, 9224, 9225, 9226, 9227, 9228, 9229, 9230, 9231,
                9232, 9233, 9234, 9235, 9236, 9237, 9238, 9239, 9240, 9241, 9242, 9243, 9244, 9245, 9246, 9247, 9248,
                9249, 9250, 9251, 9252, 9253, 9254, 9255, 9256, 9257, 9258, 9259, 9260, 9261, 9262, 9263, 9264, 9265,
                9266, 9267, 9268, 9269, 9270, 9271, 9272, 9273, 9274, 9275, 9276, 9277, 9278, 9279, 9280, 9281, 9282,
                9283, 9284, 9285, 9286, 9287, 9288, 9289, 9290, 9291, 9292, 9293, 9294, 9295, 9296, 9297, 9298, 9299,
                9300, 9301, 9302, 9303, 9304, 9305, 9306, 9307, 9308, 9309, 9310, 9311, 9312, 9313, 9314, 9315, 9316,
                9317, 9318, 9319, 9320, 9321, 9322, 9323, 9324, 9325, 9326, 9327, 9328, 9329, 9330, 9331, 9332, 9333,
                9334, 9335, 9336, 9337, 9338, 9339, 9340, 9341, 9342, 9343, 9344, 9345, 9346, 9347, 9348, 9349, 9350,
                9351, 9352, 9353, 9354, 9355, 9356, 9357, 9358, 9359, 9360, 9361, 9362, 9363, 9364, 9365, 9366, 9367,
                9368, 9369, 9370, 9371, 9372, 9373, 9374, 9375, 9376, 9377, 9378, 9379, 9380, 9381, 9382, 9383, 9384,
                9385, 9386, 9387, 9388, 9389, 9390, 9391, 9392, 9393, 9394, 9395, 9396, 9397, 9398, 9399, 9400, 9401,
                9402, 9403, 9404, 9405, 9406, 9407, 9408, 9409, 9410, 9411, 9412, 9413, 9414, 9415, 9416, 9417, 9418,
                9419, 9420, 9421, 9422, 9423, 9424, 9425, 9426, 9427, 9428, 9429, 9430, 9431, 9432, 9433, 9434, 9435,
                9436, 9437, 9438, 9439, 9440, 9441, 9442, 9443, 9444, 9445, 9446, 9447, 9448, 9449, 9450, 9451, 9452,
                9453, 9454, 9455, 9456, 9457, 9458, 9459, 9460, 9461, 9462, 9463, 9464, 9465, 9466, 9467, 9468, 9469,
                9470, 9471, 9472, 9473, 9474, 9475, 9476, 9477, 9478, 9479, 9480, 9481, 9482, 9483, 9484, 9485, 9486,
                9487, 9488, 9489, 9490, 9491, 9492, 9493, 9494, 9495, 9496, 9497, 9498, 9499, 9500, 9501, 9502, 9503,
                9504, 9505, 9506, 9507, 9508, 9509, 9510, 9511)));

        Characters cloud = new Characters("Cloud");
        int endLevel = 99;
        ArrayList<ArrayList<Integer>> allLevelHpValues = getPossibleHpValuesAtAllLevels(cloud.getCharacterName(), cloud.getLevel(), endLevel, cloud.getBaseHp());

        int level = cloud.getLevel(); // 6
        int expectedLevelIndex = 0;
        int expectedResultsIndex = 0;
        for (ArrayList<Integer> levelValues: allLevelHpValues){
            for (Integer hpValue: levelValues){
                if ((level > cloud.getLevel() && level < 9) || (level > 97 && level < 100)){
                    if (!hpValue.equals(expectedResults.get(expectedLevelIndex).get(expectedResultsIndex))){
                        System.out.println("Error: " + hpValue + " not " + expectedResults.get(expectedLevelIndex).get(expectedLevelIndex));
                        System.out.println("testHpCloudFirstTwoLevelUpsAndLastTwoLevelUpsAllPossibleValues :: Failed");
                        return false;
                    }
                }
                expectedResultsIndex++;
            }
            expectedResultsIndex = 0;
            if ((level > cloud.getLevel() && level < 9) || (level > 97 && level < 100)){
                expectedLevelIndex++;
            }
            level++;
        }

        System.out.println("testHpCloudFirstTwoLevelUpsAndLastTwoLevelUpsAllPossibleValues :: Passed");
        return true;
    }

    public static boolean testHpCloudFirstAndLastTwoLevelUpValueBoundries(){
        Characters cloud = new Characters("Cloud");
        int endLevel = 99;
        ArrayList<ArrayList<Integer>> possibleHpValuesAtAllLevels = getPossibleHpValuesAtAllLevels(cloud.getCharacterName(), cloud.getLevel(), endLevel, cloud.getBaseHp());
        ArrayList<ArrayList<Integer>> lvlUpHpResultBoundryValues = new ArrayList<>();
        lvlUpHpResultBoundryValues.add(new ArrayList<>(Arrays.asList(322, 323, 334, 335))); // lvl 7
        lvlUpHpResultBoundryValues.add(new ArrayList<>(Arrays.asList(335, 336, 353, 354))); // lvl 8
        lvlUpHpResultBoundryValues.add(new ArrayList<>(Arrays.asList(8867, 8868, 9413, 9414))); // lvl 98
        lvlUpHpResultBoundryValues.add(new ArrayList<>(Arrays.asList(8959, 8960, 9511, 9512))); // lvl 99

        int[] levelsChecked = {7, 8, 98, 99};
        int[] levelsIndexes = {1, 2, 92, 93}; // possibleHpValuesAtAllLevels.get(levelsIndexes[#])
        int levelsIndexesCounter = 0;
        for (ArrayList<Integer> boundryLvlArray: lvlUpHpResultBoundryValues){
            int boundryChecker = 1;
            for (Integer hpBoundryValue: boundryLvlArray){
                if (boundryChecker == 1 || boundryChecker == 4){ // out of bounds values
                    if (possibleHpValuesAtAllLevels.get(levelsIndexes[levelsIndexesCounter]).contains(hpBoundryValue)){
                        System.out.println("Error: " + hpBoundryValue + "hp not available value in level " + levelsChecked[levelsIndexesCounter]);
                        System.out.println("testHpCloudFirstAndLastTwoLevelUpValueBoundries :: Failed");
                        return false;
                    }
                } else { // values are within level boundry
                    if (!possibleHpValuesAtAllLevels.get(levelsIndexes[levelsIndexesCounter]).contains(hpBoundryValue)){
                        System.out.println("Error: " + hpBoundryValue + "hp not found in level " + levelsChecked[levelsIndexesCounter]);
                        System.out.println("testHpCloudFirstAndLastTwoLevelUpValueBoundries :: Failed");
                        return false;
                    }
                }
                boundryChecker++;
            }
            levelsIndexesCounter++;
        }
        System.out.println("testHpCloudFirstAndLastTwoLevelUpValueBoundries :: Passed");
        return true;
    }

    public static void printHpMpValues(ArrayList<ArrayList<ArrayList<Integer>>> rangedHpMpLevelValues, int startLvl){
        //Pre-condition - HP array must be the first array, and MP must be the second array.
        //2nd pre-condition - HP and MP Arrays must be the same size.
        int hpArray = 0;
        int mpArray = 1;

        for(int lvlIndex = 0; lvlIndex < rangedHpMpLevelValues.get(hpArray).size(); lvlIndex++){ // each level
            System.out.println("--------------------------- Level ("+ startLvl + ") ---------------------------");
            int numOfHpValuesInLvl = rangedHpMpLevelValues.get(hpArray).get(lvlIndex).size();
            int numOfMpValuesInLvl = rangedHpMpLevelValues.get(mpArray).get(lvlIndex).size();
            int numOfRows;
            int maxValuesPerRow = 6;

            // Set num of rows printed for each value (for current level)
            if (numOfHpValuesInLvl > numOfMpValuesInLvl){
                if (numOfHpValuesInLvl % maxValuesPerRow == 0) {
                    numOfRows = numOfHpValuesInLvl / maxValuesPerRow;
                } else {
                    numOfRows = (numOfHpValuesInLvl / maxValuesPerRow) + 1;
                }
            } else {
                if (numOfMpValuesInLvl % maxValuesPerRow == 0) {
                    numOfRows = numOfMpValuesInLvl / maxValuesPerRow;
                } else {
                    numOfRows = (numOfMpValuesInLvl / maxValuesPerRow) + 1;
                }
            }

            int hpValueIndex = 0;
            int mpValueIndex = 0;
            for (int row = 0; row < numOfRows; row++){
                // Print HP Values
                for (int hpValueCounter = 0; hpValueCounter < maxValuesPerRow; hpValueCounter++){
                    if (hpValueIndex > (numOfHpValuesInLvl - 1)){
                        System.out.printf("%5s", "");
                    } else {
                        System.out.printf("%5s", rangedHpMpLevelValues.get(hpArray).get(lvlIndex).get(hpValueIndex));
                        hpValueIndex++;
                    }
                }
                System.out.print(" | ");
                // Print MP Values
                for (int mpValueCounter = 0; mpValueCounter < maxValuesPerRow; mpValueCounter++){
                    if (mpValueIndex > (numOfMpValuesInLvl - 1)){
                        System.out.printf("%5s", "");
                    } else {
                        System.out.printf("%5s", rangedHpMpLevelValues.get(mpArray).get(lvlIndex).get(mpValueIndex));
                        mpValueIndex++;
                    }
                }
                System.out.println();
            }
            startLvl++;
        }
    }

    public static void printSelectedMpValuesAndRnds(ArrayList<ArrayList<Integer>> rangedMpLevelValuesWithRnds, int startLvl){
        int mpValueIndex = 0;
        int mpRndIndex = 1;
        for(int lvlIndex = 0, currentLvl = startLvl; lvlIndex < rangedMpLevelValuesWithRnds.size() - 1; lvlIndex++, currentLvl++) { // each level
            System.out.println("-------------------- Level ("+ currentLvl + ") --------------------");
            System.out.print("MP: " + rangedMpLevelValuesWithRnds.get(lvlIndex).get(mpValueIndex));
            System.out.print(" | ");
            System.out.print("Rnd: " + rangedMpLevelValuesWithRnds.get(lvlIndex).get(mpRndIndex));
            System.out.println();
        }
    }

    public static void testPrint(ArrayList<ArrayList<ArrayList<Integer>>> rangedHpMpLevelValues, int startLvl){
        for(int lvlIndex = 0; lvlIndex < rangedHpMpLevelValues.get(0).size(); lvlIndex++) { // each level
            System.out.println("-------------------- Level ("+ startLvl + ") --------------------");
            for (int value = 0; value < rangedHpMpLevelValues.get(0).get(lvlIndex).size(); value++){
                System.out.print(rangedHpMpLevelValues.get(0).get(lvlIndex).get(value) + " ");
            }
            startLvl++;
            System.out.println();
        }
    }

    //******************************************************************************************************************
    //****************************************************** HP/MP *****************************************************
    //******************************************************************************************************************



    //******************************************************************************************************************
    //************************************************** Print Methods *************************************************
    //******************************************************************************************************************

    public static void printTestResults(ArrayList<Boolean> tests, int numOfTestsRun){
        ArrayList<Integer> testResults = new ArrayList<>(Arrays.asList(0, 0, tests.size())); // Passed, Failed, Tests Executed

        for (Boolean isTestPassed: tests){
            if (isTestPassed){
                testResults.set(0, testResults.get(0) + 1);
            } else {
                testResults.set(1, testResults.get(1) + 1);
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("Test Results:: " + testResults.get(0) + " Passed | " +
                testResults.get(1) + " Failed | " +
                testResults.get(2) + "/" + numOfTestsRun + " Tests Executed");
    }

    public static void printHpAndRndValues(ArrayList<ArrayList<Integer>> hpAndRndValues, int startLvl){
        int lvlDisplay = startLvl;
        System.out.println("============= Printing selected HP values and random numbers =============");
        for (ArrayList<Integer> lvl: hpAndRndValues){
            if (lvl.size() > 1){
                System.out.println("**** LVL(" + lvlDisplay + ") ****");
                System.out.print("HP Value: " + lvl.get(0) + " | ");
                System.out.println("HP rndNum: " + lvl.get(1));
                System.out.println();
            } else if (lvl.size() == 1) {
                System.out.println("**** LVL(" + lvlDisplay + ") ****");
                System.out.println("HP Value: " + lvl.get(0));
                System.out.println();
            }
            lvlDisplay++;
        }
    }

    public static void printMpAndRndValues(ArrayList<ArrayList<Integer>> mpAndRndValues, int startLvl){
        int lvlDisplay = startLvl;
        System.out.println("============= Printing selected MP values and random numbers =============");
        for (ArrayList<Integer> lvl: mpAndRndValues){
            if (lvl.size() > 1){
                System.out.println("**** LVL(" + lvlDisplay + ") ****");
                System.out.print("MP Value: " + lvl.get(0) + " | ");
                System.out.println("MP rndNum: " + lvl.get(1));
                System.out.println();
            } else if (lvl.size() == 1) {
                System.out.println("**** LVL(" + lvlDisplay + ") ****");
                System.out.println("MP Value: " + lvl.get(0));
                System.out.println();
            }
            lvlDisplay++;
        }
        System.out.println();
    }

    //******************************************************************************************************************
    //******************************************************************************************************************
    //******************************************************************************************************************
    public static void main(String[] args) {
        int numOfTestsMethods = 3;
        //ArrayList<Boolean> numOfTestsRun = new ArrayList<>();

        //numOfTestsRun.add(testHpCloudValuesLvlsSixToSixteenAndEightyNineToNinetyNinePathsToEndLevelMaxValue());
        //numOfTestsRun.add(testHpCloudFirstTwoLevelUpsAndLastTwoLevelUpsAllPossibleValues());
        //numOfTestsRun.add(testHpCloudFirstAndLastTwoLevelUpValueBoundries());

        //printTestResults(numOfTestsRun, numOfTestsMethods);
        String characterName = "Cloud";
        int startLevel = 6;
        int endLevel = 99;
        int startHp = 314;
        int startMp = 54;

        Integer maxHpAtEndLevel = getMaxHpValueAtEndLevel(characterName, startLevel, endLevel, startHp);
        Integer maxMpAtEndLevel = 902; //getMaxMpValueAtEndLevel(characterName, startLevel, endLevel, startMp);

        System.out.println("Character Name = " + characterName);
        System.out.println("startLevel = " + startLevel);
        System.out.println("endLevel = " + endLevel);
        System.out.println("startHp = " + startHp);
        System.out.println("startMp = " + startHp);
        System.out.println("maxHpAtEndLevel = " + maxHpAtEndLevel);
        System.out.println("maxHpAtEndLevel = " + maxMpAtEndLevel);
        ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> hpMpResults = calculatePossibleRangeLevelsHpMpCombinations(characterName, startLevel, endLevel, startHp, maxHpAtEndLevel, startMp, maxMpAtEndLevel, true);
        if (hpMpResults.isEmpty()){
            System.out.println("Fail - end lvl values not obtainable");
        } else {
            System.out.println("Pass");
        }
        //printHpMpValues(hpMpResults, startLevel);
        //testPrint(hpMpResults, startLevel);


    }
}