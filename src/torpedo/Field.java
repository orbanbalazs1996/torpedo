package torpedo;

public class Field {

	enum field_state{
		not_known,
		sea,
		ship;
	}
	field_state state = field_state.sea;
	int koord_x,koord_y;
	public Field(int x,int y,field_state _state) {
		// TODO Auto-generated constructor stub
		this.koord_x = x;
		this.koord_y = y;
		this.state = _state;
	}
	field_state getFieldState() {
		return this.state;
	}
	void setFieldState(field_state s) {
		this.state=s;
	}
	int getXpos() {
		return this.koord_x;
	}
	int getYpos() {
		return this.koord_y;
	}
	
}
