package pl.ant.magica;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Controller implements Listener{
    private HashMap<UUID, Mage> mages;
    private Magica plugin;

    Controller(Magica plugin){
        this.plugin = plugin;
    }

    /**
     * Zwraca wszystkich magów
     * @return zwraca mapę magów
     */
    public HashMap<UUID, Mage> getMages() {
        return mages;
    }

    /**
     * Zwraca maga z mapy
     * @param uuid of player
     * @return mag z mapy
     */

    @Nullable
    public Mage getMage(UUID uuid){
        if(mages.containsKey(uuid)){
            return mages.get(uuid);
        }
        else{
            return addMage(pullMage(uuid));
        }
    }

    /**
     * dodaje maga do mapy
     * @param mage
     */
    @Nullable
    public Mage addMage(Mage mage){
        if(!mages.containsKey(mage.getPlayer())){
            return mages.put(mage.getPlayer().getUniqueId(), mage);
        }
        return null;
    }

    /**
     * usuwa maga z mapy
     * @param uuid
     */
    public void removeMage(UUID uuid){
        if(mages.containsKey(uuid)){
            mages.remove(uuid);
        }
    }

    /** TODO integracja z SQL - pull
     * ładuje maga z SQL do mapy
     * @param uuid uuid gracza
     * @return zwraca załadowanego maga
     */

    @Nullable
    public Mage pullMage(UUID uuid){
        return null;
    }

    /** TODO integracja z SQL - push
     * dodaje maga do SQL
     * @param mage
     */
    public void pushMage(Mage mage){
        return;
    }

    /** TODO integracja z SQL - erase
     * usuwa maga z SQL
     * @param mage mag do wymazania z SQL
     */
    public void eraseMage(Mage mage){
        return;
    }

    /** TODO integracja z SQL - backup
     * Zapisuje wszystkich magów z mapy do SQL
     */
    public void backupMages(){
        for (Map.Entry<UUID, Mage> entry : mages.entrySet()) {
            pushMage(entry.getValue());
        }
    }

    /**
     * Wywala wszystkich magów z mapy
     */
    public void dumpMages(){
        for (Map.Entry<UUID, Mage> entry : mages.entrySet()) {
            removeMage(entry.getValue().getPlayer().getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event){
        getMage(event.getPlayer().getUniqueId());

    }
}
