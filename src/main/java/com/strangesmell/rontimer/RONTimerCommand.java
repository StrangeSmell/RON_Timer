package com.strangesmell.rontimer;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;


public class RONTimerCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(RONTimer.MODID)
                .requires(source -> source.hasPermission(2)) // 确保只有具有特定权限的玩家才能执行此命令
                .then(Commands.literal("timer")
                        .then(Commands.argument("float", FloatArgumentType.floatArg())
                                .executes(RONTimerCommand::executeCommand)
                        )
                )
        );
         dispatcher.register(Commands.literal(RONTimer.MODID)
                .requires(source -> source.hasPermission(2)) // 确保只有具有特定权限的玩家才能执行此命令
                .then(Commands.literal("livingEntityTimer")
                        .then(Commands.argument("float", FloatArgumentType.floatArg())
                                .executes(RONTimerCommand::executeCommandLivingEntityTimer)
                        )
                )
        );
    }

    private static int executeCommand(CommandContext<CommandSourceStack> context) {
        float timer = FloatArgumentType.getFloat(context, "float");
        if(timer < 0){
            context.getSource().sendSystemMessage(Component.literal("Timer must be greater than 0 !"));
        }else {
            RONTimer.timer = timer;
            context.getSource().sendSystemMessage(Component.literal("Timer set to " + RONTimer.timer));
            context.getSource().sendSystemMessage(Component.literal("Can your computer withstand it ?"));
        }
        return 1;
    }
    private static int executeCommandLivingEntityTimer(CommandContext<CommandSourceStack> context) {
        float timer = FloatArgumentType.getFloat(context, "float");
        if(timer < 0){
            context.getSource().sendSystemMessage(Component.literal("Timer must be greater than 0 !"));
        }else {
            RONTimer.livingEntityTimer = timer;
            context.getSource().sendSystemMessage(Component.literal("livingEntityTravel set to " + RONTimer.livingEntityTimer));
            context.getSource().sendSystemMessage(Component.literal("Can your computer withstand it ?"));
        }
        return 1;
    }

}
