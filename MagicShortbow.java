package com.rs.game.player.actions.combat.weaponscript;

import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.utils.Utils;

/**
 * @author -Andreas 11 feb. 2020 12:21:24
 * @project 1. Avalon
 * 
 */

public class MagicShortbow extends WeaponScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 861 };
	}

	@Override
	public CombatType getType() {
		return CombatType.RANGE;
	}

	private final int[] validAmmo = { 882, 884, 886, 888, 890, 892 };

	@Override
	public boolean isValidAmmo(int ammoId) {
		for (int ids : validAmmo) {
			if (ids == ammoId) {
				return true;
			}
		}
		return false;
	}

	public int getAttackSpeed() {
		return 3;
	}

	@Override
	public int getAttackAnimation(int attackStyle) {
		return 426;
	}

	@Override
	public int getSpecialAmount() {
		return 55;
	}

	@Override
	public double getDamageMultiplier() {
		return 1.0;
	}

	@Override
	public double getAccuracyMultiplier() {
		return 1.15;
	}

	@Override
	public void sendSpecialAttack(Player player, Player target, int weaponId, int attackStyle) {
		int damage = player.getPlayerCombat().getRandomMaxHit(player, weaponId, attackStyle, true, true,
				getDamageMultiplier(), true);
		player.animate(new Animation(1074));
		player.playSound(2545, 1);
		World.sendMSBProjectile(player, target, 249);
		World.sendMSBProjectile2(player, target, 249);
		player.getPlayerCombat().delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle,
				PlayerCombat.getRangeHit(player, damage));
		player.getPlayerCombat().delayHit(Utils.getDistance(player, target) > 3 ? 3 : 1, weaponId, attackStyle,
				PlayerCombat.getRangeHit(player, damage));
		player.getPlayerCombat().dropAmmo(player, 2);
	}
}
