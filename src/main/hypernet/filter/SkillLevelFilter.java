package hypernet.filter;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;

public class SkillLevelFilter implements Filter<SkillLevelAPI> {

    @Override
    public boolean accept(SkillLevelAPI skill) {
        return skill.getLevel() > 0;
    }
}
