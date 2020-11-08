package hypernet.subject;

import java.util.List;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import hypernet.IntelSubject;
import hypernet.filter.PersonFilter;
import hypernet.filter.SkillLevelFilter;
import hypernet.helper.CollectionHelper;

public abstract class PersonSubject extends IntelSubject {

    public PersonSubject(String e, MarketAPI m) {
        super(e, m);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        addHeader(info, width);
        List<CommDirectoryEntryAPI> people = getPeople();
        addPersonCount(info, people.size());
        for (CommDirectoryEntryAPI person : people) {
            addPerson(info, (PersonAPI) person.getEntryData());
        }
    }

    protected abstract List<PersonFilter> getFilters();

    private void addPersonCount(TooltipMakerAPI info, int adminsSize) {
        String isOrAre = adminsSize == 1 ? " is " : " are ";
        String numberOrNo = adminsSize == 0 ? "no " : adminsSize + " ";
        String emptyOrS = adminsSize == 1 ? "" : "s";
        info.addPara("There" + isOrAre + numberOrNo + entity + emptyOrS + " present.", 10f);
    }

    private void addPerson(TooltipMakerAPI info, PersonAPI person) {
        List<SkillLevelAPI> skills = person.getStats().getSkillsCopy();
        CollectionHelper.reduce(skills, new SkillLevelFilter());
        int skillSize = skills.size();
        String numberOrNo = skillSize == 0 ? "no " : skillSize + " ";
        String skillOrSkills = skillSize == 1 ? "skill" : "skills";
        String dotOrColon = skillSize == 0 ? "." : ":";
        String adminPara = "%s, level " + person.getStats().getLevel() + " " + entity + " with " + numberOrNo
                + skillOrSkills + dotOrColon;
        info.addPara(adminPara, 10f, Misc.getTextColor(), Misc.getHighlightColor(), person.getNameString());
        addSkills(info, skills);
    }

    private void addSkills(TooltipMakerAPI info, List<SkillLevelAPI> skills) {
        for (SkillLevelAPI skill : skills) {
            float skillLevel = skill.getLevel();
            SkillSpecAPI skillSpec = skill.getSkill();
            TooltipMakerAPI text = info.beginImageWithText(skillSpec.getSpriteName(), 14f);
            text.addPara(skillSpec.getName() + ", level " + Math.round(skillLevel), 0f);
            info.addImageWithText(3f);
        }
    }

    private List<CommDirectoryEntryAPI> getPeople() {
        List<CommDirectoryEntryAPI> people = market.getCommDirectory().getEntriesCopy();
        CollectionHelper.reduce(people, getFilters());
        return people;
    }
}
