package pianokeyboard2;

import java.util.ArrayList;
import processing.core.PApplet;
import javax.sound.midi.*;

public class PianoKeyboard2 extends PApplet {

	float whiteW = 70;
	float blackW = 42;
	float whiteH = 400;
	float blackH = 250;
	static final int INSTRUMENT = 1;
	//change instrument here:  https://www.midi.org/specifications/item/gm-level-1-sound-set
	boolean clicked = false;
	boolean keyPressed = false;
	boolean spaceKeyPressed = false;
	ArrayList<Key> keyboard;
	
	MidiChannel channel;
	
	enum State { START, PLAYING };
	State current = State.START;

	//21 white keys
	Key c3, d3, e3, f3, g3, a3, b3, c4, d4, e4, f4, g4, a4, b4, c5, d5, e5, f5, g5, a5, b5;

	//15 black keys
	Key cSharp3, dSharp3, fSharp3, gSharp3, aSharp3, cSharp4, dSharp4, fSharp4, gSharp4, aSharp4, cSharp5, dSharp5, fSharp5, gSharp5, aSharp5;


	public static void main(String[] args)
	{
		PApplet.main("pianokeyboard2.PianoKeyboard2");
	}

	public void settings()
	{
		size(1470,450);
	}

	public void setup() {
		try {
			this.channel = initMidiChannel(INSTRUMENT);
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		
		c3 = new Key("c3",0,50,true,48,'y');
		d3 = new Key("d3",whiteW,50,true,50,'u');
		e3 = new Key("e3",whiteW*2,50,true,52,'i');
		f3 = new Key("f3",whiteW*3,50,true,53,'o');
		g3 = new Key("g3",whiteW*4,50,true,55,'p');
		a3 = new Key("a3",whiteW*5,50,true,57,'a');
		b3 = new Key("b3",whiteW*6,50,true,59,'s');
		c4 = new Key("c4",whiteW*7,50,true,60,'d');
		d4 = new Key("d4",whiteW*8,50,true,62,'f');
		e4 = new Key("e4",whiteW*9,50,true,64,'g');
		f4 = new Key("f4",whiteW*10,50,true,65,'h');
		g4 = new Key("g4",whiteW*11,50,true,67,'j');
		a4 = new Key("a4",whiteW*12,50,true,69,'k');
		b4 = new Key("b4",whiteW*13,50,true,71,'l');
		c5 = new Key("c5",whiteW*14,50,true,72,'z');
		d5 = new Key("d5",whiteW*15,50,true,74,'x');
		e5 = new Key("e5",whiteW*16,50,true,76,'c');
		f5 = new Key("f5",whiteW*17,50,true,77,'v');
		g5 = new Key("g5",whiteW*18,50,true,79,'b');
		a5 = new Key("a5",whiteW*19,50,true,81,'n');
		b5 = new Key("b5",whiteW*20,50,true,83,'m');

		cSharp3 = new Key("cSharp3",whiteW - (blackW/2),50,false,49,'1');
		dSharp3 = new Key("dSharp3",whiteW*2 - (blackW/2),50,false,51,'2');
		fSharp3 = new Key("fSharp3",whiteW*4 - (blackW/2),50,false,54,'3');
		gSharp3 = new Key("gSharp3",whiteW*5 - (blackW/2),50,false,56,'4');
		aSharp3 = new Key("aSharp3",whiteW*6 - (blackW/2),50,false,58,'5');
		cSharp4 = new Key("cSharp4",whiteW*8 - (blackW/2),50,false,61,'6');
		dSharp4 = new Key("dSharp4",whiteW*9 - (blackW/2),50,false,63,'7');
		fSharp4 = new Key("fSharp4",whiteW*11 - (blackW/2),50,false,66,'8');
		gSharp4 = new Key("gSharp4",whiteW*12 - (blackW/2),50,false,68,'9');
		aSharp4 = new Key("aSharp4",whiteW*13 - (blackW/2),50,false,70,'0');
		cSharp5 = new Key("cSharp5",whiteW*15 - (blackW/2),50,false,73,'q');
		dSharp5 = new Key("dSharp5",whiteW*16 - (blackW/2),50,false,75,'w');
		fSharp5 = new Key("fSharp5",whiteW*18 - (blackW/2),50,false,78,'e');
		gSharp5 = new Key("gSharp5",whiteW*19 - (blackW/2),50,false,80,'r');
		aSharp5 = new Key("aSharp5",whiteW*20 - (blackW/2),50,false,82,'t');

		keyboard = new ArrayList<Key>();
		keyboard.add(c3);
		keyboard.add(d3);
		keyboard.add(e3);
		keyboard.add(f3);
		keyboard.add(g3);
		keyboard.add(a3);
		keyboard.add(b3);
		keyboard.add(c4);
		keyboard.add(d4);
		keyboard.add(e4);
		keyboard.add(f4);
		keyboard.add(g4);
		keyboard.add(a4);
		keyboard.add(b4);
		keyboard.add(c5);
		keyboard.add(d5);
		keyboard.add(e5);
		keyboard.add(f5);
		keyboard.add(g5);
		keyboard.add(a5);
		keyboard.add(b5);
		keyboard.add(cSharp3);
		keyboard.add(dSharp3);
		keyboard.add(fSharp3);
		keyboard.add(gSharp3);
		keyboard.add(aSharp3);
		keyboard.add(cSharp4);
		keyboard.add(dSharp4);
		keyboard.add(fSharp4);
		keyboard.add(gSharp4);
		keyboard.add(aSharp4);
		keyboard.add(cSharp5);
		keyboard.add(dSharp5);
		keyboard.add(fSharp5);
		keyboard.add(gSharp5);
		keyboard.add(aSharp5);
	}
	
	private static MidiChannel initMidiChannel(int instrument) throws MidiUnavailableException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel channel = synth.getChannels()[0];
        // MIDI instruments are traditionally numbered from 1,
        // but the javax.midi API numbers them from 0
        channel.programChange(instrument - 1);
        //channel.setChannelPressure(5);  // optional vibrato
        return channel;
    }
	
	public void draw()
	{
		switch(current)
		{
		case START:
			drawInstructions();
			break;
		case PLAYING:
			drawKeyboard();
			break;
		}
	}
	
	public void drawInstructions()
	{
		background(0);
		fill(255);
		textAlign(CENTER, CENTER);
		textSize(32);
		text("Welcome to your 3-Octave Piano Keyboard!",735,150);
		text("Click anywhere or press any key to start.",735,185);
		textAlign(CENTER, TOP);
		textSize(16);
		text("**Hold the space bar to play multiple notes simultaneously, "
				+ "or click and drag your mouse.",735,230);
	}

	public void drawKeyboard() {
		background(255);
		fill(0);
		textAlign(CENTER, BOTTOM);
		textSize(40);
		text("Your playing is music to my ears!",735,40);
		for(Key pianoKey : keyboard)
		{
			pianoKey.draw();
		}
	}
	
	public void mousePressed()
	{
		switch(current)
		{
		case START:
			mousePressedStart();
			break;
		case PLAYING:
			mousePressedKeyboard();
			break;
		}
	}
	
	public void mousePressedStart()
	{
		current = State.PLAYING;
	}
	
	public void mousePressedKeyboard()
	{
		for(int i = 0; i < keyboard.size(); i++){
			keyboard.get(i).onClick(mouseX, mouseY);
		}
	}
	
	public void mouseDragged()
	{
		for(int i = 0; i < keyboard.size(); i++){
			keyboard.get(i).onClick(mouseX, mouseY);
		}
	}

	public void mouseReleased()
	{
		if(keyPressed == false)
		{
			for(int i = 0; i < keyboard.size(); i++)
			{
				keyboard.get(i).unpress();
			}
		}
	}

	public void keyPressed()
	{
		switch(current)
		{
		case START:
			keyPressedStart();
			break;
		case PLAYING:
			keyTyped();
			break;
		}
	}

	public void keyPressedStart()
	{
		current = State.PLAYING;
	}

	public void keyPressedKeyboard()
	{
		keyPressed = true;
	}

	public void keyReleased()
	{
		if(keyCode == ' ')
		{
			spaceKeyPressed = false;
		}
		if(spaceKeyPressed == false)
		{
			keyPressed = false;
			for(int i = 0; i < keyboard.size(); i++)
			{
				keyboard.get(i).unpress();
			}
		}
	}

	public void keyTyped()
	{
		if(keyCode == ' ')
		{
			this.keyPressedKeyboard();
			spaceKeyPressed = true;
		}

		for(int i = 0; i < keyboard.size(); i++)
		{
			keyboard.get(i).checkKeyTyped(key);
		}

	}

	public class Key
	{
		//top left corner of the key
		String note;
		private float x;
		private float y;
		private float w;
		private float h;
		char keyType;
		private int midiNote;
		
		private boolean pressed;
		private boolean isWhite;

		public Key(String n, float newX, float newY, boolean tempIsWhite, int newNote, char newKeyType)
		{
			note = n;
			x = newX;
			y = newY;
			isWhite = tempIsWhite;
			pressed = false;
			midiNote = newNote;
			keyType = newKeyType;
			if(isWhite)
			{
				w = whiteW;
				h = whiteH;
			}
			else
			{
				w = blackW;
				h = blackH;
			}
		}

		public void draw()
		{
			if(pressed)
			{
				fill(124,255,135);
				this.play();
				
			}
			else if(isWhite == false)
			{
				fill(0);
			}
			else
			{
				noFill();
			}
			rect(x,y,w,h);
		}

		public void draw(int r, int g, int b)
		{
			fill(r,g,b);
			rect(x,y,w,h);
		}

		public void onClick(int clickedX, int clickedY)
		{
			if(isWhite == false)
			{
				if((clickedX > x && clickedX < x + blackW) && (clickedY > y && clickedY < y + blackH))
				{
					this.press();
				}
			}
			else
			{
				if(this.note.substring(0, 1).equals("e")||this.note.substring(0, 1).equals("b"))
				{
					if(((clickedX>x+blackW/2 && clickedX<x+w)&&(clickedY>y && clickedY<y+blackH))
							||((clickedX>x && clickedX<x+w) && (clickedY>y+blackH && clickedY<y+whiteH)))
					{
						this.press();
					}
				}
				if(this.note.substring(0, 1).equals("c")||this.note.substring(0, 1).equals("f"))
				{
					if(((clickedX>x && clickedX<x+w-blackW/2)&&(clickedY>y && clickedY<y+blackH))
							||((clickedX>x && clickedX<x+w) && (clickedY>y+blackH && clickedY<y+whiteH)))
					{
						this.press();
					}
				}
				if(this.note.substring(0, 1).equals("d")||this.note.substring(0, 1).equals("g")||this.note.substring(0, 1).equals("a"))
				{
					if(((clickedX>x+blackW/2 && clickedX<x+w-blackW/2)&&(clickedY>y && clickedY<y+blackH))
							||((clickedX>x && clickedX<x+w) && (clickedY>y+blackH && clickedY<y+whiteH)))
					{
						this.press();
					}
				}
			}
		}
		
		public void press()
		{
			pressed = true;
		}

		public void unpress()
		{
			pressed = false;
			this.unplay();
		}
		
		public void play()
		{
			channel.noteOn(midiNote,75);
		}
		
		public void unplay()
		{
			channel.noteOff(midiNote);
		}
		
		public void checkKeyTyped(char keyTyped)
		{
			if(keyTyped == keyType)
			{
				this.press();
			}
		}
	}
}

