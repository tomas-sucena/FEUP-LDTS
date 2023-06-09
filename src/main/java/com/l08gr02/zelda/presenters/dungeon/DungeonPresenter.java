package com.l08gr02.zelda.presenters.dungeon;

import com.l08gr02.zelda.gui.GUI;
import com.l08gr02.zelda.models.dungeon.Dungeon;
import com.l08gr02.zelda.models.elements.CollidingElement;
import com.l08gr02.zelda.models.elements.Element;
import com.l08gr02.zelda.models.elements.Hitbox;
import com.l08gr02.zelda.models.elements.moving.Link;
import com.l08gr02.zelda.models.elements.moving.monsters.Monster;
import com.l08gr02.zelda.models.elements.tiles.Heart;
import com.l08gr02.zelda.presenters.Presenter;
import com.l08gr02.zelda.presenters.elements.LinkPresenter;
import com.l08gr02.zelda.presenters.elements.monsters.MonsterPresenter;
import com.l08gr02.zelda.presenters.elements.monsters.MonsterPresenterFactory;
import com.l08gr02.zelda.viewers.dungeon.DungeonViewer;
import com.l08gr02.zelda.viewers.elements.moving.LinkViewer;

import java.util.ArrayList;
import java.util.List;

import static com.l08gr02.zelda.presenters.GameplayPresenter.ACTION;

public class DungeonPresenter extends Presenter<Dungeon> {
    private LinkPresenter linkPresenter;
    private List<MonsterPresenter> monsterPresenters;

    // constructor
    public DungeonPresenter(Dungeon model, DungeonViewer viewer) {
        super(model, viewer);

        linkPresenter = new LinkPresenter(model.getLink(), new LinkViewer());
        monsterPresenters = new MonsterPresenterFactory().createPresenters(model);
    }

    // methods
    @Override
    public void update(GUI gui) {
        checkCollisions();

        viewer.draw(gui, model);
        linkPresenter.update(gui);

        for (MonsterPresenter monsterPresenter : monsterPresenters){
            monsterPresenter.update(gui);
        }
    }

    public void setLinkActions(List<ACTION> actions){
        linkPresenter.setActions(actions);
    }

    public void checkCollisions(){
        checkLinkCollisions();

        // verify if the monsters are colliding
        for (Monster monster : model.getMonsters()){
            checkMonsterCollisions(monster);
        }

        // verificar se o Link está a atacar
        if (!linkPresenter.isAttacking()) {
            return;
        }

        Link link = model.getLink();
        Hitbox swordHitbox = link.getAttackHitbox();

        for (MonsterPresenter monsterPresenter : monsterPresenters){
            Monster monster = (Monster) monsterPresenter.getModel();

            if (!monster.getHitbox().intersects(swordHitbox)){
                continue;
            }

            monsterPresenter.takeDamage(1, link.getDirection());
        }

        model.getMonsters().removeIf(monster -> monster.getHearts() <= 0);
        monsterPresenters.removeIf(monsterPresenter -> monsterPresenter.getModel().getHearts() <= 0);
    }

    public void checkLinkCollisions(){
        Link link = model.getLink();

        List<CollidingElement> linkObstacles = new ArrayList<>();

        for (Element tile : model.getTiles()){
            if (tile instanceof CollidingElement el){
                linkObstacles.add(el);
            }
        }

        for (Monster monster : model.getMonsters()){
            if (link.collidesWith(monster)){
                linkPresenter.bump(link.getDirection());
                linkObstacles.add(monster);

                break;
            }
        }

        for (Heart heart : model.getHearts()){
            if (link.collidesWith(heart)){
                linkPresenter.heal(1);
                model.getHearts().remove(heart);

                break;
            }
        }

        link.setObstacles(linkObstacles);
    }

    public void checkMonsterCollisions(Monster monster){
        List<CollidingElement> monsterObstacles = new ArrayList<>();

        for (Monster m : model.getMonsters()){
            if (monster == m || !monster.collidesWith(m)){
                continue;
            }

            ACTION direction = monster.getDirection();

            // mudar a direção dos monstros que colidem
            monster.setDirection(m.getDirection());
            m.setDirection(direction);

            break;
        }

        for (Element tile : model.getTiles()){
            if (tile instanceof CollidingElement el){
                monsterObstacles.add(el);
            }
        }

        for (Heart heart : model.getHearts()){
            if (monster.collidesWith(heart)){
                monster.heal(1);
                model.getHearts().remove(heart);

                break;
            }
        }

        monster.setObstacles(monsterObstacles);
    }

}
