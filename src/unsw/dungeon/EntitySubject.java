package unsw.dungeon;

public interface EntitySubject {
    
	public void addObserver(DungeonObserver o);
    public void pingObservers();
	public void removeObserver(DungeonObserver o);
	
}