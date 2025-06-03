package mino;
import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyBinding;
import main.PlayManager;
public class Mino {
	public Block b[]=new Block[5];
	public Block tempB[]=new Block[5];
	int autoDropCounter=0;
	public int direction=1;
	boolean leftCollision, rightCollision, bottomCollision;
	public boolean active=true;
	public boolean deactivating;
	int deactivateCounter=0;
	
	
	public void create(Color c) {
		for (int i = 0; i < 5; i++) {
            b[i] = new Block(c);
            tempB[i] = new Block(c);
        }
	}
	public void setXY(int x, int y) {}
	public void updateXY(int direction) {
		/*for (int i = 0; i < b.length; i++) {
            b[i].x = tempB[i].x + centerX;
            b[i].y = tempB[i].y + centerY;
        }
        this.direction = direction;*/
		checkMovementCollision();
		if(leftCollision==false&&rightCollision==false&&bottomCollision==false){
		this.direction=direction;
		for(int i=0; i<5; i++) {
			b[i].x=tempB[i].x;
			b[i].y=tempB[i].y;
		    } 
		}
	}
	

	public void getDirection1() {}
	public void getDirection2() {}
	public void getDirection3() {}
	public void getDirection4() {}
	public void checkMovementCollision() {
		
		leftCollision=false;
		rightCollision=false;
		bottomCollision=false;
		
		checkStaticBlockCollision();
		for (int i = 0; i < b.length; i++) {
	        if (b[i].x == PlayManager.left_x) leftCollision = true;
	        if (b[i].x + Block.SIZE == PlayManager.right_x) rightCollision = true;
	        if (b[i].y + Block.SIZE == PlayManager.bottom_y) bottomCollision = true;
	    }
	}
	public void checkRotationCollision() {
	    leftCollision = rightCollision = bottomCollision = false;
	    checkStaticBlockCollision();

	    for (int i = 0; i < b.length; i++) {
	        if (tempB[i].x < PlayManager.left_x) leftCollision = true;
	        if (tempB[i].x + Block.SIZE > PlayManager.right_x) rightCollision = true;
	        if (tempB[i].y + Block.SIZE > PlayManager.bottom_y) bottomCollision = true;
	    }

	    if (leftCollision || rightCollision || bottomCollision) {
	        // Wall kick
	        int[] kickOffsets = {-2 * Block.SIZE, -Block.SIZE, Block.SIZE, 2 * Block.SIZE};
	        for (int offset : kickOffsets) {
	            boolean canKick = true;
	            for (int i = 0; i < b.length; i++) {
	                int newX = tempB[i].x + offset;
	                int newY = tempB[i].y; 

	                // wall and static block check
	                if (newX < PlayManager.left_x || newX + Block.SIZE > PlayManager.right_x) {
	                    canKick = false;
	                    break;
	                }

	                for (int j = 0; j < PlayManager.staticBlocks.size(); j++) {
	                    int targetX = PlayManager.staticBlocks.get(j).x;
	                    int targetY = PlayManager.staticBlocks.get(j).y;
	                    if (newX == targetX && newY == targetY) {
	                        canKick = false;
	                        break;
	                    }
	                }
	            }

	            if (canKick) {
	                for (int i = 0; i < 5; i++) tempB[i].x += offset;
	                leftCollision = rightCollision = bottomCollision = false;
	                return;
	            }
	        }
	    }
	}


	private void checkStaticBlockCollision() {
	    for (int i = 0; i < PlayManager.staticBlocks.size(); i++) {
	        int targetX = PlayManager.staticBlocks.get(i).x;
	        int targetY = PlayManager.staticBlocks.get(i).y;

	        for (int j = 0; j < b.length; j++) {
	            if (b[j].y + Block.SIZE == targetY && b[j].x == targetX) bottomCollision = true;
	            if (b[j].x - Block.SIZE == targetX && b[j].y == targetY) leftCollision = true;
	            if (b[j].x + Block.SIZE == targetX && b[j].y == targetY) rightCollision = true;
	        }
	    }
	}
	public void update() {
		
		if(deactivating) {
			deactivating();
		}
		//movement
		if(KeyBinding.upPressed) {

			switch(direction) {
			case 1:getDirection2();break;
			case 2:getDirection3();break;
			case 3:getDirection4();break;
			case 4:getDirection1();break;
			}
			KeyBinding.upPressed=false;
			GamePanel.sfx.play(3, false);
		}
		
		checkMovementCollision();
		
        if(KeyBinding.downPressed) {
        	if(bottomCollision==false) {
			for(int i=0; i<5; i++) {
				b[i].y += Block.SIZE;
			}
			autoDropCounter=0;
        	}
			KeyBinding.downPressed=false;
		}
        if(KeyBinding.leftPressed) {
        	if(leftCollision==false) {
        	for(int i=0; i<5; i++) {
				b[i].x -= Block.SIZE;
			    }
			}
			KeyBinding.leftPressed=false;
			
		}
        if(KeyBinding.rightPressed) {
        	if(rightCollision==false) {
        	for(int i=0; i<5; i++) {
				b[i].x += Block.SIZE;
			    }
        	}
			KeyBinding.rightPressed=false;
	
        }
        if(bottomCollision) {
        	if(deactivating==false) {
        		GamePanel.sfx.play(4, false);
        	}
        	deactivating=true;
        	
        }
        else {
		autoDropCounter++;
		if(autoDropCounter==PlayManager.dropInterval) {
			//fall
			b[0].y+=Block.SIZE;
			b[1].y+=Block.SIZE;
			b[2].y+=Block.SIZE;
			b[3].y+=Block.SIZE;
			b[4].y+=Block.SIZE;
			autoDropCounter=0;
			}
        }
	}
	private void deactivating() {
		deactivateCounter++;
		//45 frames until deactivation
		if(deactivateCounter==45) {
			deactivateCounter=0;
			checkMovementCollision();
			//checking the bottom
			if(bottomCollision) {
				active=false;
			}
		}
	}
	public void draw(Graphics2D g2) {
		int space=2;
		g2.setColor(b[0].c);
		for(int i=0; i<5; i++) {
		g2.fillRect(b[i].x+space, b[i].y+space, Block.SIZE-(space*2), Block.SIZE-(space*2));
		}
	}
}
