package hypernet.filter;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;

public class PersonOfficer implements PersonFilter {

    @Override
    public boolean accept(CommDirectoryEntryAPI entry) {
        PersonAPI person = (PersonAPI) entry.getEntryData();
        return person.getPostId().equals(Ranks.POST_MERCENARY);
    }
}
