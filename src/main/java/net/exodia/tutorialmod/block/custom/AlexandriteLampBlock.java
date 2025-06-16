package net.exodia.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class AlexandriteLampBlock extends Block {
    //need booleanproperty because minecraft uses a single instance of a block
    // so it cant be updated with a normal variable
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");

    public AlexandriteLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CLICKED, Boolean.valueOf(false)));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        //need it to be server side
        if(!pLevel.isClientSide()) {
            boolean currentState = pState.getValue(CLICKED);
            pLevel.setBlockAndUpdate(pPos, pState.setValue(CLICKED, !currentState));
        }

        return InteractionResult.SUCCESS; //gives right hand animation
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(CLICKED);
    }
}
