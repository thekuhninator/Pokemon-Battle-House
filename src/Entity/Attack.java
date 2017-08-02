//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Entity;

public class Attack {
    private String move;
    private int type;
    private int pp;
    private int currPP;
    private int damage;
    private int cur_damage;
    private int accuracy;
    private String description;
    private boolean physical;
    private boolean special;
    private boolean status;
    private final int NORMAL = 0;
    private final int SUPEREFFECTIVE = 1;
    private final int NOTVERYEFFECTIVE = 2;
    private final int NOEFFECT = 3;

    public Attack(String name) {
        this.move = name;
        this.init();
        this.currPP = this.pp;
    }

    public void init() {
        String var1 = this.move;
        switch(this.move.hashCode()) {
            case -2118493157:
                if(var1.equals("Quick Attack")) {
                    this.type = 0;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 7;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case -1918458087:
                if(var1.equals("Ice Punch")) {
                    this.type = 14;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 4;
                    this.damage = 75;
                    this.accuracy = 100;
                }

                return;
            case -1850529456:
                if(var1.equals("Return")) {
                    this.type = 0;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case -1797481490:
                if(var1.equals("Tackle")) {
                    this.type = 0;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 10;
                    this.damage = 50;
                    this.accuracy = 100;
                }

                return;
            case -1635590347:
                if(var1.equals("Night Slash")) {
                    this.type = 16;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case -1559832272:
                if(var1.equals("Razor Leaf")) {
                    this.type = 11;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 3;
                    this.damage = 55;
                    this.accuracy = 95;
                }

                return;
            case -1423304081:
                if(var1.equals("Dark Pulse")) {
                    this.type = 16;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 4;
                    this.damage = 80;
                    this.accuracy = 100;
                }

                return;
            case -1301871756:
                if(var1.equals("Synthesis")) {
                    this.type = 11;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 2;
                    this.accuracy = 200;
                }

                return;
            case -1272268528:
                if(var1.equals("Zen Headbutt")) {
                    this.type = 13;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case -1097144317:
                if(var1.equals("Body Slam")) {
                    this.type = 0;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 4;
                    this.damage = 85;
                    this.accuracy = 100;
                }

                return;
            case -1061378785:
                if(var1.equals("Posion Jab")) {
                    this.type = 3;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case -1032149916:
                if(var1.equals("Ice Beam")) {
                    this.type = 14;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 4;
                    this.damage = 95;
                    this.accuracy = 100;
                }

                return;
            case -937930460:
                if(var1.equals("Blaze Kick")) {
                    this.type = 9;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 4;
                    this.damage = 85;
                    this.accuracy = 90;
                }

                return;
            case -924533057:
                if(var1.equals("Nightmare")) {
                    this.type = 7;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 3;
                    this.accuracy = 100;
                }

                return;
            case -852493214:
                if(var1.equals("Sleep Powder")) {
                    this.type = 11;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 4;
                    this.accuracy = 75;
                }

                return;
            case -851562341:
                if(var1.equals("Take Down")) {
                    this.type = 0;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case -704034086:
                if(var1.equals("Scratch")) {
                    this.type = 0;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 6;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case -609882791:
                if(var1.equals("Thunderbolt")) {
                    this.type = 12;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 4;
                    this.damage = 95;
                    this.accuracy = 100;
                }

                return;
            case -609270339:
                if(!var1.equals("Thunderwave")) {
                    return;
                }
                break;
            case -600620312:
                if(var1.equals("Iron Defense")) {
                    this.type = 8;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case -458937047:
                if(var1.equals("Icy Wind")) {
                    this.type = 14;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 6;
                    this.damage = 55;
                    this.accuracy = 90;
                }

                return;
            case -60373940:
                if(var1.equals("Dragon Claw")) {
                    this.type = 15;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 80;
                    this.accuracy = 100;
                }

                return;
            case -41438851:
                if(var1.equals("Hypnosis")) {
                    this.type = 13;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 3;
                    this.accuracy = 100;
                }

                return;
            case -9007796:
                if(var1.equals("Double Team")) {
                    this.type = 0;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 3;
                    this.accuracy = 200;
                }

                return;
            case 68706:
                if(var1.equals("Dig")) {
                    this.type = 4;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 6;
                    this.damage = 80;
                    this.accuracy = 100;
                }

                return;
            case 2070808:
                if(var1.equals("Bite")) {
                    this.type = 16;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case 2483517:
                if(var1.equals("Peck")) {
                    this.type = 2;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 35;
                    this.accuracy = 100;
                }

                return;
            case 44453119:
                if(var1.equals("Shadow Ball")) {
                    this.type = 7;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 4;
                    this.damage = 80;
                    this.accuracy = 100;
                }

                return;
            case 67067591:
                if(var1.equals("Ember")) {
                    this.type = 9;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case 69076633:
                if(var1.equals("Growl")) {
                    this.type = 0;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 5;
                    this.accuracy = 100;
                }

                return;
            case 81001239:
                if(var1.equals("Toxic")) {
                    this.type = 3;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 2;
                    this.accuracy = 100;
                }

                return;
            case 254532796:
                if(var1.equals("Nasty Plot")) {
                    this.type = 16;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 3;
                    this.accuracy = 200;
                }

                return;
            case 410702022:
                if(var1.equals("Mud Bomb")) {
                    this.type = 4;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 5;
                    this.damage = 65;
                    this.accuracy = 85;
                }

                return;
            case 570322678:
                if(var1.equals("Light Screen")) {
                    this.type = 13;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 3;
                    this.accuracy = 200;
                }

                return;
            case 916350240:
                if(var1.equals("Bug Bight")) {
                    this.type = 6;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case 926390840:
                if(var1.equals("Iron Head")) {
                    this.type = 8;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case 926744744:
                if(var1.equals("Iron Tail")) {
                    this.type = 8;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 4;
                    this.damage = 100;
                    this.accuracy = 75;
                }

                return;
            case 1210189728:
                if(var1.equals("Flamethrower")) {
                    this.type = 9;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 4;
                    this.damage = 95;
                    this.accuracy = 100;
                }

                return;
            case 1393001461:
                if(var1.equals("Psychic")) {
                    this.type = 13;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 3;
                    this.damage = 90;
                    this.accuracy = 100;
                }

                return;
            case 1416544052:
                if(var1.equals("Aqua Tail")) {
                    this.type = 10;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 4;
                    this.damage = 90;
                    this.accuracy = 90;
                }

                return;
            case 1444284282:
                if(var1.equals("Pursuit")) {
                    this.type = 16;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 200;
                }

                return;
            case 1646243883:
                if(var1.equals("Agillity")) {
                    this.type = 0;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case 1658583020:
                if(var1.equals("Fire Spin")) {
                    this.type = 9;
                    this.physical = false;
                    this.status = true;
                    this.special = true;
                    this.pp = 8;
                    this.damage = 35;
                    this.accuracy = 85;
                }

                return;
            case 1748120079:
                if(var1.equals("Extrasensory")) {
                    this.type = 13;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case 1788225143:
                if(var1.equals("Water Gun")) {
                    this.type = 10;
                    this.physical = false;
                    this.status = false;
                    this.special = true;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            case 1869360520:
                if(!var1.equals("Tail Whip")) {
                    return;
                }

                this.type = 0;
                this.physical = false;
                this.status = true;
                this.special = false;
                this.pp = 5;
                this.accuracy = 100;
                break;
            case 1898272873:
                if(var1.equals("Bulk Up")) {
                    this.type = 1;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 3;
                    this.accuracy = 200;
                }

                return;
            case 2068984711:
                if(var1.equals("Force Palm")) {
                    this.type = 1;
                    this.physical = true;
                    this.status = false;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 60;
                    this.accuracy = 100;
                }

                return;
            case 2137017798:
                if(var1.equals("Fake Tears")) {
                    this.type = 16;
                    this.physical = false;
                    this.status = true;
                    this.special = false;
                    this.pp = 5;
                    this.damage = 40;
                    this.accuracy = 100;
                }

                return;
            default:
                return;
        }

        this.type = 12;
        this.physical = true;
        this.status = true;
        this.special = false;
        this.pp = 2;
        this.accuracy = 100;
    }

    public int getCurrentDamage() {
        return this.cur_damage;
    }

    public void getDamage(Pokemon user, Pokemon enemy) {
        String var3 = this.move;
        switch(this.move.hashCode()) {
            case -2118493157:
                if(!var3.equals("Quick Attack")) {
                    ;
                }

                return;
            case -1797481490:
                if(!var3.equals("Tackle")) {
                    ;
                }

                return;
            case -1711003554:
                if(!var3.equals("Thundershock")) {
                    ;
                }

                return;
            case -852493214:
                if(var3.equals("Sleep Powder")) {
                    if(enemy.getStatusEffect() == 4) {
                        System.out.println(enemy.getName() + " is already asleep!");
                    } else {
                        enemy.setStatusEffect(4);
                        System.out.println(enemy.getName() + " has fallen asleep");
                    }

                    return;
                }

                return;
            case -704034086:
                if(!var3.equals("Scratch")) {
                    ;
                }

                return;
            case -609270339:
                if(!var3.equals("Thunderwave")) {
                    return;
                }
                break;
            case -458937047:
                if(!var3.equals("Icy Wind")) {
                    ;
                }

                return;
            case 68706:
                if(!var3.equals("Dig")) {
                    ;
                }

                return;
            case 2483517:
                if(!var3.equals("Peck")) {
                    ;
                }

                return;
            case 67067591:
                if(!var3.equals("Ember")) {
                    ;
                }

                return;
            case 69076633:
                if(var3.equals("Growl")) {
                    if(enemy.getCurrAttack() <= -6) {
                        System.out.println("It had no effect!");
                    } else {
                        enemy.setCurrAttack(enemy.getCurrAttack() - 1);
                        System.out.println(enemy.getName() + "'s attack has been lowered!");
                    }

                    return;
                }

                return;
            case 81001239:
                if(var3.equals("Toxic")) {
                    if(enemy.getStatusEffect() == 3) {
                        System.out.println("It had no effect!");
                    } else {
                        enemy.setStatusEffect(3);
                        System.out.println(enemy.getName() + " has been posioned");
                    }

                    return;
                }

                return;
            case 1444284282:
                if(!var3.equals("Pursuit")) {
                    ;
                }

                return;
            case 1658583020:
                if(var3.equals("Fire Spin") && enemy.getStatusEffect() != 2) {
                    enemy.setStatusEffect(2);
                    System.out.println(enemy.getName() + " has been burned");
                }

                return;
            case 1788225143:
                if(!var3.equals("Water Gun")) {
                    ;
                }

                return;
            case 1869360520:
                if(var3.equals("Tail Whip")) {
                    if(enemy.getCurrDefense() <= -6) {
                        System.out.println("It had no effect!");
                    } else {
                        enemy.setCurrDefense(enemy.getCurrDefense() - 1);
                        System.out.println(enemy.getName() + "'s defense has been lowered");
                    }
                    break;
                }

                return;
            default:
                return;
        }

        if(enemy.getStatusEffect() == 1) {
            System.out.println("The enemy is already paralyzed!");
        } else {
            enemy.setStatusEffect(1);
            System.out.println(enemy.getName() + " has been paralyzed");
        }

    }

    public int calculateDamage(Pokemon user, Pokemon enemy) {
        double stab = 1.0D;
        int critical = (int)(Math.random() * (double)(user.getBaseSpeed() / 512)) == 0?2:1;
        if(this.type == user.getType() || this.type == user.getType2()) {
            stab = 1.5D;
        }

        double modifier = stab * Pokemon.getEffectiveness(this.type, enemy.getType(), enemy.getType2()) * (double)critical * (Math.random() * 0.15D + 0.85D);
        if(this.physical) {
            return (int)((double)(2 * enemy.getLevel() + 10) / 250.0D * (double)user.getAttack() * Pokemon.getStatChange(user.getCurrAttack()) / ((double)enemy.getDefense() * Pokemon.getStatChange(enemy.getCurrDefense())) * (double)(this.damage + 2) * modifier);
        } else if(this.special) {
            return (int)((double)(2 * enemy.getLevel() + 10) / 250.0D * (double)user.getSpecAttack() * Pokemon.getStatChange(user.getCurrSpecAttack()) / ((double)enemy.getSpecDefense() * Pokemon.getStatChange(enemy.getCurrSpecDefense())) * (double)(this.damage + 2) * modifier);
        } else {
            if(this.status) {
                String var8 = this.move;
                switch(this.move.hashCode()) {
                    case -1301871756:
                        if(var8.equals("Synthesis")) {
                            this.type = 11;
                            user.setHp(user.getMaxHP());
                        }

                        return 0;
                    case -852493214:
                        if(var8.equals("Sleep Powder")) {
                            this.type = 11;
                            enemy.setStatusEffect(3);
                        }

                        return 0;
                    case -609270339:
                        if(!var8.equals("Thunderwave")) {
                            return 0;
                        }
                        break;
                    case -600620312:
                        if(var8.equals("Iron Defense")) {
                            this.type = 8;
                            user.setCurrDefense(user.getCurrDefense() + 2);
                        }

                        return 0;
                    case -41438851:
                        if(var8.equals("Hypnosis")) {
                            this.type = 13;
                            enemy.setStatusEffect(3);
                        }

                        return 0;
                    case -9007796:
                        if(var8.equals("Double Team")) {
                            this.type = 0;
                            user.setCurrEvasiveness(user.getCurrEvasiveness() + 1);
                        }

                        return 0;
                    case 69076633:
                        if(var8.equals("Growl")) {
                            this.type = 0;
                            enemy.setCurrAttack(enemy.getCurrAttack() - 1);
                        }

                        return 0;
                    case 81001239:
                        if(var8.equals("Toxic")) {
                            this.type = 3;
                            enemy.setStatusEffect(2);
                        }

                        return 0;
                    case 254532796:
                        if(var8.equals("Nasty Plot")) {
                            this.type = 16;
                            user.setCurrSpecAttack(user.getCurrSpecAttack() + 2);
                        }

                        return 0;
                    case 570322678:
                        if(var8.equals("Light Screen")) {
                            this.type = 13;
                            user.setCurrSpecDefense(user.getCurrSpecDefense() + 1);
                        }

                        return 0;
                    case 1646243883:
                        if(var8.equals("Agillity")) {
                            this.type = 0;
                            user.setCurrSpeed(user.getCurrSpeed() + 2);
                        }

                        return 0;
                    case 1869360520:
                        if(!var8.equals("Tail Whip")) {
                            return 0;
                        }

                        this.type = 0;
                        enemy.setCurrDefense(enemy.getCurrDefense() - 1);
                        break;
                    case 1898272873:
                        if(var8.equals("Bulk Up")) {
                            this.type = 1;
                            user.setCurrAttack(user.getCurrAttack() + 1);
                            user.setCurrDefense(user.getCurrDefense() + 1);
                        }

                        return 0;
                    case 2137017798:
                        if(var8.equals("Fake Tears")) {
                            this.type = 16;
                            enemy.setCurrDefense(enemy.getCurrDefense() - 2);
                        }

                        return 0;
                    default:
                        return 0;
                }

                this.type = 12;
                enemy.setStatusEffect(1);
            }

            return 0;
        }
    }

    public String getName() {
        return this.move;
    }

    public int getMaxPP() {
        return this.pp;
    }

    public int getCurrentPP() {
        return this.currPP;
    }

    public int getType() {
        return this.type;
    }

    public String getDescription() {
        return "A Damaging Attack";
    }
}
