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
import hypernet.filter.PersonAdministrator;
import hypernet.filter.SkillLevelFilter;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;

public class AdminSubject extends IntelSubject {

    public AdminSubject(MarketAPI m) {
        super("administrator", m);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        addHeader(info, width);
        List<CommDirectoryEntryAPI> admins = getAdmins();
        addAdminCount(info, admins.size());
        for (CommDirectoryEntryAPI admin : admins) {
            addAdmin(info, (PersonAPI) admin.getEntryData());
        }
    }

    @Override
    public boolean isAvailable() {
        return MarketHelper.has(market);
    }

    private void addAdminCount(TooltipMakerAPI info, int adminsSize) {
        String isOrAre = adminsSize == 1 ? "is" : "are";
        String numberOrNo = adminsSize == 0 ? " no" : " " + adminsSize;
        String adminOrAdmins = adminsSize == 1 ? "administrator" : "administrators";
        info.addPara("There " + isOrAre + numberOrNo + " freelance " + adminOrAdmins + " present.", 10f);
    }

    private void addAdmin(TooltipMakerAPI info, PersonAPI admin) {
        List<SkillLevelAPI> skills = admin.getStats().getSkillsCopy();
        CollectionHelper.reduce(skills, new SkillLevelFilter());
        int skillSize = skills.size();
        String numberOrNo = skillSize == 0 ? "no " : skillSize + " ";
        String skillOrSkills = skillSize == 1 ? "skill" : "skills";
        String dotOrColon = skillSize == 0 ? "." : ":";
        String adminPara = "%s, level " + admin.getStats().getLevel() + " freelance administrator with " + numberOrNo
                + skillOrSkills + dotOrColon;
        info.addPara(adminPara, 10f, Misc.getTextColor(), Misc.getHighlightColor(), admin.getNameString());
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

    private List<CommDirectoryEntryAPI> getAdmins() {
        List<CommDirectoryEntryAPI> people = market.getCommDirectory().getEntriesCopy();
        CollectionHelper.reduce(people, new PersonAdministrator());
        return people;
    }
}
