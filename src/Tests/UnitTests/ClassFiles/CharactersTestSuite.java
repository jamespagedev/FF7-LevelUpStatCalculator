package ClassFiles;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import static ClassFiles.Characters.characterNames;

public class CharactersTestSuite {
    /***************************************************************************************
     ********************************* Constructor Testset *********************************
     **************************************************************************************/
    @Test
    public void testConstructorCloud(){
        Characters cloud = new Characters("Cloud");
    }

    @Test
    public void testConstructorBarret(){
        Characters barret = new Characters("Barret");
    }

    @Test
    public void testConstructorTifa(){
        Characters tifa = new Characters("Tifa");
    }

    @Test
    public void testConstructorAeris(){
        Characters aeris = new Characters("Aeris");
    }

    @Test
    public void testConstructorRedXIII(){
        Characters redXIII = new Characters("Red XIII");
    }

    @Test
    public void testConstructorYuffie(){
        Characters yuffie = new Characters("Yuffie");
    }

    @Test
    public void testConstructorCaitSith(){
        Characters caitSith = new Characters("Cait Sith");
    }

    @Test
    public void testConstructorVincent(){
        Characters vincent = new Characters("Vincent");
    }

    @Test
    public void testConstructorCid(){
        Characters cid = new Characters("Cid");
    }

    private Characters cloud = new Characters("Cloud");
    private Characters barret = new Characters("Barret");
    private Characters tifa = new Characters("Tifa");
    private Characters aeris = new Characters("Aeris");
    private Characters redXIII = new Characters("Red XIII");
    private Characters yuffie = new Characters("Yuffie");
    private Characters caitSith = new Characters("Cait Sith");
    private Characters vincent = new Characters("Vincent");
    private Characters cid = new Characters("Cid");

    /*************************************************************************************
     ********************************* Variables Testset *********************************
     *************************************************************************************/
    @Test
    public void testCharacterNamesArray(){
        // Names must be in order of... Cloud, Barret, Tifa, Aeris, Red XIII, Yuffie, Cait Sith, Vincent, Cid

        assertThat(characterNames[0], is("Cloud"));
        assertThat(characterNames[1], is("Barret"));
        assertThat(characterNames[2], is("Tifa"));
        assertThat(characterNames[3], is("Aeris"));
        assertThat(characterNames[4], is("Red XIII"));
        assertThat(characterNames[5], is("Yuffie"));
        assertThat(characterNames[6], is("Cait Sith"));
        assertThat(characterNames[7], is("Vincent"));
        assertThat(characterNames[8], is("Cid"));
    }

    @Test
    public void testCharacterName(){
        assertThat(cloud.getCharacterName(), is("Cloud"));
        assertThat(barret.getCharacterName(), is("Barret"));
        assertThat(tifa.getCharacterName(), is("Tifa"));
        assertThat(aeris.getCharacterName(), is("Aeris"));
        assertThat(redXIII.getCharacterName(), is("Red XIII"));
        assertThat(yuffie.getCharacterName(), is("Yuffie"));
        assertThat(caitSith.getCharacterName(), is("Cait Sith"));
        assertThat(vincent.getCharacterName(), is("Vincent"));
        assertThat(cid.getCharacterName(), is("Cid"));
    }

    @Test
    public void testDefaultLevel(){
        assertThat(cloud.getLevel(), is(6));
        assertThat(barret.getLevel(), is(1));
        assertThat(tifa.getLevel(), is(1));
        assertThat(aeris.getLevel(), is(1));
        assertThat(redXIII.getLevel(), is(1));
        assertThat(yuffie.getLevel(), is(1));
        assertThat(caitSith.getLevel(), is(1));
        assertThat(vincent.getLevel(), is(1));
        assertThat(cid.getLevel(), is(1));
    }

    @Test
    public void testLevelRanges(){
        // Cloud Level Ranges (Note: Since young cloud starts at level one and has the same level up attributes, we will start at range level 1, even though cloud starts at level 6)
        assertThat(cloud.getLevelRanges()[0], is(1));
        assertThat(cloud.getLevelRanges()[cloud.getLevelRanges().length - 1], is(99));

        // Barret Level Ranges
        assertThat(barret.getLevelRanges()[0], is(1));
        assertThat(barret.getLevelRanges()[barret.getLevelRanges().length - 1], is(99));

        // Tifa Level Ranges
        assertThat(tifa.getLevelRanges()[0], is(1));
        assertThat(tifa.getLevelRanges()[tifa.getLevelRanges().length - 1], is(99));

        // Aeris Level Ranges
        assertThat(aeris.getLevelRanges()[0], is(1));
        assertThat(aeris.getLevelRanges()[aeris.getLevelRanges().length - 1], is(99));

        // Red XIII Level Ranges
        assertThat(redXIII.getLevelRanges()[0], is(1));
        assertThat(redXIII.getLevelRanges()[redXIII.getLevelRanges().length - 1], is(99));

        // Yuffie Level Ranges
        assertThat(yuffie.getLevelRanges()[0], is(1));
        assertThat(yuffie.getLevelRanges()[yuffie.getLevelRanges().length - 1], is(99));

        // Cait Sith Level Ranges
        assertThat(caitSith.getLevelRanges()[0], is(1));
        assertThat(caitSith.getLevelRanges()[caitSith.getLevelRanges().length - 1], is(99));

        // Vincent Level Ranges
        assertThat(vincent.getLevelRanges()[0], is(1));
        assertThat(vincent.getLevelRanges()[vincent.getLevelRanges().length - 1], is(99));

        // Cid Level Ranges
        assertThat(cid.getLevelRanges()[0], is(1));
        assertThat(cid.getLevelRanges()[cid.getLevelRanges().length - 1], is(99));
    }

    @Test
    public void testDefaultWpnMateriaSlots(){
        // Cloud default materia setup
        assertThat(cloud.getWpnMateriaSlots().length, is(2));
        assertThat(cloud.getMateriaInWpnSlot(1).getMateriaName(), is("Lightning"));
        assertThat(cloud.getMateriaInWpnSlot(1).isSitchHpMpEnabled(), is(false));
        assertThat(cloud.getMateriaInWpnSlot(1).getHpIncDecPerc(), is(-0.02));
        assertThat(cloud.getMateriaInWpnSlot(1).getMpIncDecPerc(), is(0.02));
        assertThat(cloud.getMateriaInWpnSlot(1).getDexIncDecPerc(), is(0.0));
        assertThat(cloud.getMateriaInWpnSlot(1).getMagicIncDecPerc(), is(0.0));
        assertThat(cloud.getMateriaInWpnSlot(1).getLuckIncDecPerc(), is(0.0));
        assertThat(cloud.getMateriaInWpnSlot(2).getMateriaName(), is("Ice"));
        assertThat(cloud.getMateriaInWpnSlot(2).isSitchHpMpEnabled(), is(false));
        assertThat(cloud.getMateriaInWpnSlot(2).getHpIncDecPerc(), is(-0.02));
        assertThat(cloud.getMateriaInWpnSlot(2).getMpIncDecPerc(), is(0.02));
        assertThat(cloud.getMateriaInWpnSlot(2).getDexIncDecPerc(), is(0.0));
        assertThat(cloud.getMateriaInWpnSlot(2).getMagicIncDecPerc(), is(0.0));
        assertThat(cloud.getMateriaInWpnSlot(2).getLuckIncDecPerc(), is(0.0));

        // Barret default materia setup
        assertThat(barret.getWpnMateriaSlots().length, is(1));
        assertThat(barret.getMateriaInWpnSlot(1).getMateriaName(), is("Empty"));
        assertThat(barret.getMateriaInWpnSlot(1).isSitchHpMpEnabled(), is(false));
        assertThat(barret.getMateriaInWpnSlot(1).getHpIncDecPerc(), is(0.0));
        assertThat(barret.getMateriaInWpnSlot(1).getMpIncDecPerc(), is(0.0));
        assertThat(barret.getMateriaInWpnSlot(1).getDexIncDecPerc(), is(0.0));
        assertThat(barret.getMateriaInWpnSlot(1).getMagicIncDecPerc(), is(0.0));
        assertThat(barret.getMateriaInWpnSlot(1).getLuckIncDecPerc(), is(0.0));
    }

    @Test
    public void testDefaultIsSwitchHpMpMateriaEquiped(){
        assertThat(cloud.isSwitchHpMpMateriaEquiped(), is(false));
        assertThat(barret.isSwitchHpMpMateriaEquiped(), is(false));
        assertThat(tifa.isSwitchHpMpMateriaEquiped(), is(false));
        assertThat(aeris.isSwitchHpMpMateriaEquiped(), is(false));
        assertThat(redXIII.isSwitchHpMpMateriaEquiped(), is(false));
        assertThat(yuffie.isSwitchHpMpMateriaEquiped(), is(false));
        assertThat(caitSith.isSwitchHpMpMateriaEquiped(), is(false));
        assertThat(vincent.isSwitchHpMpMateriaEquiped(), is(false));
        assertThat(cid.isSwitchHpMpMateriaEquiped(), is(false));
    }
}
