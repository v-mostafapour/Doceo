package model;

public class Mention {
	private String name;
	private String pos;
	private String ne;

	public Mention(String name, String pos, String ne) {
		this.setName(name);
		this.setPos(pos);
		this.setNe(ne);
	}

	public String getName() {
		return name;
	}

	public void setName(String word) {
		this.name = word;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getNe() {
		return ne;
	}

	public void setNe(String ne) {
		this.ne = ne;
	}

	public String toString() {
		return "name:" + getName() + "--pos:" + getPos() + "--ne:" + getNe();
	}
}
