package herb.herb.commands;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.Bukkit;
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
        GlobalArgus Gargs = new GlobalArgus();
        switch (type){
            case"randomeffect":
                @NotNull String settingtype = args[1];
                if (!(settingtype.equals("true") || settingtype.equals("false"))){
                    sender.sendMessage("ture 또는 false만 입력하세요");
                    return false;
                }else{
                    Gargs.randomEffect = true;
                }
                break;
            case "team":
                switch (args[1]){
                    case "create":
                        @NotNull List<String> playerlist = new ArrayList();
                        for (int i = 3; i > args.length-2; i++) playerlist.add(Bukkit.getPlayer(args[i]).getName());
                        GlobalArgus.ServerConfig.setComments("teams."+args[2],playerlist);
                        break;
                    case "add":
                        playerlist = GlobalArgus.ServerConfig.getComments("teams."+args[2]);
                        for (int i = 3; i > args.length-2; i++) playerlist.add(Bukkit.getPlayer(args[i]).getName());
                        GlobalArgus.ServerConfig.setComments("teams."+args[2],playerlist);
                        break;
                    case "kick":
                        playerlist = GlobalArgus.ServerConfig.getComments("teams."+args[2]);
                        for (int i = 3; i > args.length-2; i++) playerlist.remove(Bukkit.getPlayer(args[i]).getName());
                        GlobalArgus.ServerConfig.setComments("teams."+args[2],playerlist);
                        break;
                    case "remove":
                        GlobalArgus.ServerConfig.setComments("teams."+args[2],new ArrayList());
                        break;
                }
        }
        return true;
    }
    List settinglist = new ArrayList();
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equals("setting")){
            if (args.length == 1){
                settinglist.add("randomeffect");
                return settinglist;
            }
        }
        return null;
    }
}
