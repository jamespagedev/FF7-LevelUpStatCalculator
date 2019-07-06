/**
 * Created by James Page on 5/2/2017.
 */
package ClassFiles;

public class TestCharacters {
    public boolean createCloudTestSet(){
        boolean testResult = true; // Pass
        Characters cloud = new Characters("Cloud");

        // Cloud name is correct
        if (!(cloud.getCharacterName().equals("Cloud"))){
            System.out.println("Name " + cloud.getCharacterName() + " is not \"Cloud\"");
            return testResult;
        }

        // Cloud starting weapon
        if (!(cloud.getEquipedWeapon().getWeaponName().equals("Buster Sword"))){
            System.out.println("Weapon " + cloud.getEquipedWeapon() + " is not \"Buster Sword\"");
            return testResult;
        }

        // Cloud starting armor
        if (!(cloud.getEquipedArmor().getArmorName().equals("Bronze Bangle"))){
            System.out.println("Armor " + cloud.getEquipedArmor() + " is not \"Bronze Bangle\"");
            return testResult;
        }

        // Cloud starting accessory
        if (!(cloud.getEquipedAccessory().getAccessoryName().equals("None"))){
            System.out.println("Accessory " + cloud.getEquipedAccessory() + " is not \"None\"");
            return testResult;
        }

        String[] expectedMateriaInSlot = {"Lightning", "Ice"};
        for (int materiaSlotNum = 1, expectedMateriaInSlotIndex = 0; materiaSlotNum <= cloud.getEquipedWeapon().getNumOfMateriaSlots(); materiaSlotNum++, expectedMateriaInSlotIndex++){
            if (!(cloud.getMateriaInWpnSlot(materiaSlotNum).getMateriaName().equals(expectedMateriaInSlot[expectedMateriaInSlotIndex]))) {
                System.out.println("Materia in weapon slot " + materiaSlotNum + " = \"" +
                        cloud.getMateriaInWpnSlot(materiaSlotNum).getMateriaName() +
                        "\". Instead, it should = " + expectedMateriaInSlot[expectedMateriaInSlotIndex]);
                return false;
            }
        }

        // Cloud starting Hit Points
        if (!(cloud.getHp() == 302)){
            System.out.println("HP " + cloud.getHp() + " is not \"302\"");
            return testResult;
        }

        // Cloud starting Magic Points
        if (!(cloud.getMp() == 56)){
            System.out.println("MP " + cloud.getMp() + " is not \"56\"");
            return testResult;
        }

        // Cloud starting Strength
        if (!(cloud.getStrength() == 18)){
            System.out.println("Strength " + cloud.getSpirit() + " is not \"18\"");
            return testResult;
        }

        // Cloud starting Dexterity
        if (!(cloud.getDexterity() == 6)){
            System.out.println("Dexterity " + cloud.getSpirit() + " is not \"6\"");
            return testResult;
        }

        // Cloud starting Vitality
        if (!(cloud.getVitality() == 16)){
            System.out.println("Vitality " + cloud.getVitality() + " is not \"16\"");
            return testResult;
        }

        // Cloud starting Magic
        if (!(cloud.getMagic() == 23)){
            System.out.println("Magic " + cloud.getMagic() + " is not \"23\"");
            return testResult;
        }

        // Cloud starting Spirit
        if (!(cloud.getSpirit() == 17)){
            System.out.println("Spirit " + cloud.getSpirit() + " is not \"17\"");
            return testResult;
        }

        // Cloud starting Luck
        if (!(cloud.getLuck() == 14)){
            System.out.println("Luck " + cloud.getLuck() + " is not \"14\"");
            return testResult;
        }


        System.out.println("createCloudTestSet :: Pass");
        return testResult;
    }
    //public boolean createBarretTestSet(){}
    //public boolean createTifaTestSet(){}
    //public boolean createAerisTestSet(){}
    //public boolean createRedXIIITestSet(){}
    //public boolean createYuffieTestSet(){}
    //public boolean createCaitSithTestSet(){}
    //public boolean createVincentTestSet(){}
    //public boolean createCidTestSet(){}

    public void printCharacterInfo(Characters character){
        System.out.println("Character name = " + character.getCharacterName());
        System.out.println();
        System.out.println("Character weapon = " + character.getEquipedWeapon().getWeaponName() +
                " (" + character.getEquipedWeapon().getNumOfMateriaSlots() + " Materia Slots)");
        System.out.println("Character armor = " + character.getEquipedArmor().getArmorName() +
                " (" + character.getEquipedArmor().getNumOfMateriaSlots() + " Materia Slots)");
        System.out.println("Character accessory = " + character.getEquipedAccessory().getAccessoryName());
        System.out.println();
        for (int materiaSlotNum = 1; materiaSlotNum <= character.getEquipedWeapon().getNumOfMateriaSlots(); materiaSlotNum++){
            System.out.println("Materia in weapon slot " + materiaSlotNum + " = " + character.getMateriaInWpnSlot(materiaSlotNum).getMateriaName());
        }
        System.out.println();
        for (int materiaSlotNum = 1; materiaSlotNum <= character.getEquipedArmor().getNumOfMateriaSlots(); materiaSlotNum++){
            System.out.println("Materia in Armor slot " + materiaSlotNum + " = " + character.getMateriaInArmSlot(materiaSlotNum).getMateriaName());
        }
        System.out.println("Character HP = " + character.getHp());
        System.out.println("Character MP = " + character.getMp());
        System.out.println("Character Strength = " + character.getStrength());
        System.out.println("Character Dexterity = " + character.getDexterity());
        System.out.println("Character Vitality = " + character.getVitality());
        System.out.println("Character Magic = " + character.getMagic());
        System.out.println("Character Spirit = " + character.getSpirit());
        System.out.println("Character Luck = " + character.getLuck());
    }

    public static void main(String[] args) {
        TestCharacters tester = new TestCharacters();
        Characters cloud = new Characters("Cloud");
        tester.printCharacterInfo(cloud);
        tester.createCloudTestSet();

        // Test equipping weapon and materia with stats
        cloud.equipWeapon("Mythril Saber");
        tester.printCharacterInfo(cloud);
        cloud.equipMateriaInWeapon(3, "Fire");
        tester.printCharacterInfo(cloud);

        /*
        cloud.equipWeapon("Hardedge");
        tester.printCharacterInfo(cloud);
        cloud.equipWeapon("Buster Sword");
        tester.printCharacterInfo(cloud);
        cloud.equipWeapon("Nail Bat");
        tester.printCharacterInfo(cloud);
        cloud.equipWeapon("Buster Sword");
        tester.printCharacterInfo(cloud);
        */

        /* Test equipping armor and materia with stats
        cloud.equipArmor("Iron Bangle");
        tester.printCharacterInfo(cloud);
        cloud.equipMateriaInArmor(1, "Fire");
        tester.printCharacterInfo(cloud);
        cloud.equipArmor("Titan Bangle");
        tester.printCharacterInfo(cloud);
        cloud.equipArmor("Iron Bangle");
        tester.printCharacterInfo(cloud);
        cloud.equipArmor("Bronze Bangle");
        tester.printCharacterInfo(cloud);
        cloud.equipArmor("Iron Bangle");
        tester.printCharacterInfo(cloud);
        */

        /* Test equipping accessory with stats
        cloud.equipAccessory("Toughness Ring");
        tester.printCharacterInfo(cloud);
        */

    }
}
