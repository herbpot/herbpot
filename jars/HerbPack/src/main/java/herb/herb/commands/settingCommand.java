package herb.herb.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class settingCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        @NotNull String type = args[0];
        switch (type){
            case"sendmsg":
                @NotNull String settingtype = args[1];
                if (!(settingtype.equals("true") || settingtype.equals("false"))){
                    sender.sendMessage("ture 또는 false만 입력하세요");
                    return false;
                }else{

                }
                break;
        }
        return true;
    }
    List settinglist = new ArrayList();
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equals("setting")){
            if (args.length == 1){
                settinglist.add("sendmsg");
                return settinglist;
            }
        }
        return null;
    }
}
