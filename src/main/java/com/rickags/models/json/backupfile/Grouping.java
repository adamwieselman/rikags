package com.rickags.models.json.backupfile;

import java.util.List;

public class Grouping
{
   String groupName;
   List<PlayerBreakdown> playerBreakdowns;

   public String getGroupName ()
   {
      return groupName;
   }

   public void setGroupName (String groupName)
   {
      this.groupName = groupName;
   }

   public List<PlayerBreakdown> getPlayerBreakdowns ()
   {
      return playerBreakdowns;
   }

   public void setPlayerBreakdowns (List<PlayerBreakdown> playerBreakdowns)
   {
      this.playerBreakdowns = playerBreakdowns;
   }
}
