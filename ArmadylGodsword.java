package com.rs.game.player.actions.combat.weaponscript;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.PlayerCombat;

/**
 * @author -Andreas 11 feb. 2020 12:21:24
 * @project 1. Avalon
 * 
 */

public class ArmadylGodsword extends WeaponScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 11694 };
	}

	
	//NOTE: Melee weapons load attackspeed from cache
	@Override
	public int getType() {
		return MELEE;
	}

	@Override
	public int getAttackAnimation(int attackStyle) {
		switch (attackStyle) {
		case 2:
			return 7048;
		case 3:
			return 7049;
		default:
			return 7041;
		}
	}

	@Override
	public int getBlockAnimation() {
		return 7050;
	}

	@Override
	public int getSpecialAmount() {
		return 50;
	}

	@Override
	public double getDamageMultiplier() {
		return 1.375;
	}

	@Override
	public void sendSpecialAttack(Player player, int weaponId, int attackStyle) {
		int damage = player.getPlayerCombat().getRandomMaxHit(player, weaponId, attackStyle, false, true,
				getDamageMultiplier(), true);
		player.setNextAnimationNoPriority(new Animation(11989), player);
		player.gfx(new Graphics(2113));
		player.getPlayerCombat().delayNormalHit(weaponId, attackStyle, PlayerCombat.getMeleeHit(player, damage));
	}
}
