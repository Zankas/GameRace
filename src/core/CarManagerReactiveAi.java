package core;

public class CarManagerReactiveAi extends AbstractCarManager {

	// LAST PIECE I WAS.
	BlockRoadObject lastPiece;

	// BOOLEAN FOR SETTING DIRECTION AFTER THE GAME START.
	boolean alpha = true;

	// CLASS OF POINT FOR AI.
	TargetPoint target;
	TargetPoint targetTemp;

	// VARIABLES FOR HANDLING TARGET CLASS UPDATE.
	boolean updateTargetCondition;
	boolean handlingSteering;
	int targetIndex;
	int caseAi;

	// DIRECTION FOR ARTIFICIAL INTELLIGENCE.
	private Direction aiDirection;

	// MAXIMUM DISTANCE BEFORE STARTING TO STEER.
	final double steeringDistance = 40;

	// MAXIMUM AND MINIMUN ACCELERATION.
	final static double AXEL = 1.8, BRAKE = 1.0, AXELONTURNING = 1.3;

	public CarManagerReactiveAi(World w, Car car) {
		super(w, car);
		this.lastPiece = world.getMatrixWorld().whereAmI(car);
		this.target = new TargetPoint();
		this.targetTemp = new TargetPoint();
		this.updateTargetCondition = true;
		this.targetIndex = 1;
		this.caseAi = 0;
		this.handlingSteering = false;
	}

	// START AI HANDLER

	private Direction reconDirection() {
		if (car.getAngle() == 0.0) { // 0.0
			return Direction.RIGHT;
		} else if (car.getAngle() == Math.PI) {
			return Direction.LEFT;
		} else if (car.getAngle() == Math.PI / 2) {
			return Direction.DOWN;
		}
		return Direction.UP;
	}

	@Override
	void totalMove() {
		if (alpha) {
			setAiDirection(reconDirection());
			alpha = false;
			car.moveXYrot(world);
		}

		if (car.getSpeed() <= BRAKE) {
			accel();
		} else if (car.getSpeed() >= AXEL) {
			brake();
		}

		if (updateTargetCondition) {
			updateTargetCondition = false;
			int temp = world.getMatrixWorld().pointBlock(this, targetTemp);
			System.out.println(temp);
			if (temp != -1)
				caseAi = world.getMatrixWorld().pointBlock(this, target);
			if (car.getID() == 2)
				System.out.println(car.getID() + " UPDATE " + caseAi);
		}

		switch (caseAi) {
		case 1:
			if (targetIndex == 0) {
				if (car.getY4rot() < target.getPointY(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getY3rot() > target.getPointY(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.max(car.getX4rot(), car.getX3rot()) > target.getPointX(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if (Math.max(car.getX4rot(), car.getX3rot()) + steeringDistance < target.getPointX(targetIndex)) {
					if (car.getY4rot() < target.getPointY(targetIndex)) {
						car.setRIGHT(true);
						car.setLEFT(false);
					} else if (car.getY3rot() > target.getPointY(targetIndex)) {
						car.setRIGHT(false);
						car.setLEFT(true);
					} else {
						car.setRIGHT(false);
						car.setLEFT(false);
					}
				} else {
					if (car.getSpeed() > AXELONTURNING)
						brake();
					// accel();
					car.setRIGHT(true);
					car.setLEFT(false);
					handlingSteering = true;
				}
				if (handlingSteering) {
					targetIndex++;
					handlingSteering = false;
					aiDirection = Direction.DOWN;
				}
			} else if (targetIndex == 2) {
				if (handlingSteering) {
					if (car.getAngle() >= Math.PI / 2) {
						handlingSteering = false;
						targetIndex++;
					}

				}
				// else {
				// if (car.getX4rot() > target.getPointX(targetIndex)) {
				// car.setRIGHT(true);
				// car.setLEFT(false);
				// } else if (car.getX3rot() < target.getPointX(targetIndex)) {
				// car.setRIGHT(false);
				// car.setLEFT(true);
				// } else {
				// car.setRIGHT(false);
				// car.setLEFT(false);
				// }
				// if (Math.max(car.getX4rot(), car.getX3rot()) >=
				// target.getPointX(targetIndex)) {
				// targetIndex++;
				// }
				// }
			} else {
				// aiDirection = Direction.DOWN;
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 2:
			if (targetIndex == 0) {
				if (car.getX4rot() < target.getPointX(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getX3rot() > target.getPointX(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.min(car.getY4rot(), car.getY3rot()) <= target.getPointY(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if ((Math.min(car.getY4rot(), car.getY3rot() - steeringDistance) <= target.getPointY(targetIndex))) {
					if (car.getX4rot() < target.getPointX(targetIndex)) {
						car.setRIGHT(true);
						car.setLEFT(false);
					} else if (car.getX3rot() > target.getPointX(targetIndex)) {
						car.setRIGHT(false);
						car.setLEFT(true);
					} else {
						car.setRIGHT(false);
						car.setLEFT(false);
					}
				} else {
					if (car.getSpeed() > AXELONTURNING)
						brake();
					// accel();
					car.setRIGHT(false);
					car.setLEFT(true);
					handlingSteering = true;
				}
				if (handlingSteering) {
					// targetIndex++;
					handlingSteering = false;
					aiDirection = Direction.LEFT;
				}
			} else if (targetIndex == 2) {
				if (handlingSteering) {
					if (car.getAngle() <= Math.PI) {
						handlingSteering = false;
						targetIndex++;
					}
				}
				// else {
				// if (car.getX4rot() > target.getPointX(targetIndex)) {
				// car.setRIGHT(true);
				// car.setLEFT(false);
				// } else if (car.getX3rot() < target.getPointX(targetIndex)) {
				// car.setRIGHT(false);
				// car.setLEFT(true);
				// } else {
				// car.setRIGHT(false);
				// car.setLEFT(false);
				// }
				// if (Math.min(car.getX4rot(), car.getX3rot()) <
				// target.getPointX(targetIndex)) {
				// targetIndex++;
				// }
				// }
			} else {
				// aiDirection = Direction.DOWN;
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 3:
			if (targetIndex == 0) {
				if (car.getY4rot() < target.getPointY(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getY3rot() > target.getPointY(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.max(car.getX4rot(), car.getX3rot()) > target.getPointX(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if (Math.max(car.getX4rot(), car.getX3rot()) + steeringDistance < target.getPointX(targetIndex)) {
					if (car.getY4rot() < target.getPointY(targetIndex)) {
						car.setRIGHT(true);
						car.setLEFT(false);
					} else if (car.getY3rot() > target.getPointY(targetIndex)) {
						car.setRIGHT(false);
						car.setLEFT(true);
					} else {
						car.setRIGHT(false);
						car.setLEFT(false);
					}
				} else {
					if (car.getSpeed() > AXELONTURNING)
						brake();
					// accel();
					car.setRIGHT(false);
					car.setLEFT(true);
					handlingSteering = true;
				}
				if (handlingSteering) {
					// targetIndex++;
					handlingSteering = false;
					aiDirection = Direction.UP;
				}
			} else if (targetIndex == 2) {
				if (handlingSteering) {
					if (car.getAngle() >= Math.PI && car.getAngle() <= Math.PI / 2 * 3) {
						handlingSteering = false;
						// targetIndex++;
						car.setLEFT(false);
					}
				} else {
					if (car.getX4rot() < target.getPointX(targetIndex)) {
						car.setRIGHT(true);
						car.setLEFT(false);
					} else if (car.getX3rot() > target.getPointX(targetIndex)) {
						car.setRIGHT(false);
						car.setLEFT(true);
					} else {
						car.setRIGHT(false);
						car.setLEFT(false);
					}
					if (Math.min(car.getX4rot(), car.getX3rot()) <= target.getPointX(targetIndex)) {
						targetIndex++;
					}
				}
			} else {
				// aiDirection = Direction.DOWN;
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 4:
			if (targetIndex == 0) {
				if (car.getX4rot() > target.getPointX(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getX3rot() < target.getPointX(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.max(car.getY4rot(), car.getY3rot()) > target.getPointY(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if (Math.max(car.getY4rot(), car.getY3rot()) + steeringDistance < target.getPointY(targetIndex)) {
					if (car.getX4rot() > target.getPointX(targetIndex)) {
						car.setRIGHT(true);
						car.setLEFT(false);
					} else if (car.getX3rot() < target.getPointX(targetIndex)) {
						car.setRIGHT(false);
						car.setLEFT(true);
					} else {
						car.setRIGHT(false);
						car.setLEFT(false);
					}
				} else {
					if (car.getSpeed() > AXELONTURNING)
						brake();
					// accel();
					car.setRIGHT(true);
					car.setLEFT(false);
					handlingSteering = true;
				}
				if (handlingSteering) {
					targetIndex++;
					aiDirection = Direction.LEFT;
				}
			} else if (targetIndex == 2) {
				if (handlingSteering) {
					if (car.getAngle() >= Math.PI) {
						handlingSteering = false;
						targetIndex++;
					}
				}
				// else {
				// if (car.getX4rot() > target.getPointX(targetIndex)) {
				// car.setRIGHT(true);
				// car.setLEFT(false);
				// } else if (car.getX3rot() < target.getPointX(targetIndex)) {
				// car.setRIGHT(false);
				// car.setLEFT(true);
				// } else {
				// car.setRIGHT(false);
				// car.setLEFT(false);
				// }
				// if (Math.min(car.getX4rot(), car.getX3rot()) <
				// target.getPointX(targetIndex)) {
				// targetIndex++;
				// }
				// }
			} else {
				// aiDirection = Direction.DOWN;
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 5:
			if (targetIndex == 0) {
				if (car.getY4rot() > target.getPointY(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getY3rot() < target.getPointY(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.min(car.getX4rot(), car.getX3rot()) < target.getPointX(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if (Math.min(car.getX4rot(), car.getX3rot()) - steeringDistance > target.getPointX(targetIndex)) {
					if (car.getY4rot() > target.getPointY(targetIndex)) {
						car.setRIGHT(true);
						car.setLEFT(false);
					} else if (car.getY3rot() < target.getPointY(targetIndex)) {
						car.setRIGHT(false);
						car.setLEFT(true);
					} else {
						car.setRIGHT(false);
						car.setLEFT(false);
					}
				} else {
					if (car.getSpeed() > AXELONTURNING)
						brake();
					car.setRIGHT(false);
					car.setLEFT(true);
					handlingSteering = true;
				}
				if (handlingSteering) {
					targetIndex++;
					aiDirection = Direction.DOWN;
					// setAiDirection(Direction.DOWN);
				}
			} else if (targetIndex == 2) {
				if (handlingSteering)
					if (car.getAngle() <= Math.PI / 2) {
						handlingSteering = false;
						targetIndex++;
					}
				// accel();
				// } else {
				// if (car.getX4rot() > target.getPointX(targetIndex)) {
				// car.setRIGHT(true);
				// car.setLEFT(false);
				// } else if (car.getX3rot() <
				// target.getPointX(targetIndex)) {
				// car.setRIGHT(false);
				// car.setLEFT(true);
				// } else {
				// car.setRIGHT(false);
				// car.setLEFT(false);
				// }
				// if (Math.max(car.getY4rot(), car.getY3rot()) >=
				// target.getPointX(targetIndex)) {
				// targetIndex++;
				// }
				// }
			} else {
				// aiDirection = Direction.DOWN;
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 6:
			if (targetIndex == 0) {
				if (car.getX4rot() < target.getPointX(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getX3rot() > target.getPointX(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.min(car.getY4rot(), car.getY3rot()) < target.getPointY(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if ((Math.min(car.getY4rot(), car.getY3rot() - steeringDistance) <= target.getPointY(targetIndex))) {
					if (car.getX4rot() < target.getPointX(targetIndex)) {
						car.setRIGHT(true);
						car.setLEFT(false);
					} else if (car.getX3rot() > target.getPointX(targetIndex)) {
						car.setRIGHT(false);
						car.setLEFT(true);
					} else {
						car.setRIGHT(false);
						car.setLEFT(false);
					}
				} else {
					if (car.getSpeed() > AXELONTURNING)
						brake();
					// accel();
					System.out.println("6 == 1 else");
					car.setRIGHT(true);
					car.setLEFT(false);
					handlingSteering = true;
				}
				if (handlingSteering) {
					// targetIndex++;
					handlingSteering = false;
					aiDirection = Direction.LEFT;
				}
			} else if (targetIndex == 2) {
				if (handlingSteering) {
					// HAS TO BE >0, ALWAYS POSITIVE, USED A FAKED VALUE.
					if (car.getAngle() <= Math.PI / 2) {
						handlingSteering = false;
						targetIndex++;
					}
				} else {
					if (Math.min(car.getX4rot(), car.getX3rot()) < target.getPointX(targetIndex)) {
						targetIndex++;
					} else {
						if (car.getX4rot() > target.getPointX(targetIndex)) {
							car.setRIGHT(true);
							car.setLEFT(false);
						} else if (car.getX3rot() < target.getPointX(targetIndex)) {
							car.setRIGHT(false);
							car.setLEFT(true);
						} else {
							car.setRIGHT(false);
							car.setLEFT(false);
						}
						if (Math.min(car.getX4rot(), car.getX3rot()) < target.getPointX(targetIndex)) {
							targetIndex++;
						}
					}
				}
			} else {
				// aiDirection = Direction.DOWN;
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 7:
			if (targetIndex == 0) {
				if (car.getY4rot() > target.getPointY(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getY3rot() < target.getPointY(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.min(car.getX4rot(), car.getX3rot()) < target.getPointX(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if (Math.min(car.getX4rot(), car.getX3rot()) - steeringDistance > target.getPointX(targetIndex)) {
					if (car.getY4rot() > target.getPointY(targetIndex)) {
						car.setRIGHT(true);
						car.setLEFT(false);
					} else if (car.getY3rot() < target.getPointY(targetIndex)) {
						car.setRIGHT(false);
						car.setLEFT(true);
					} else {
						car.setRIGHT(false);
						car.setLEFT(false);
					}
				} else {
					if (car.getSpeed() > AXELONTURNING)
						brake();
					// accel();
					car.setRIGHT(true);
					car.setLEFT(false);
					handlingSteering = true;
				}
				if (handlingSteering) {
					targetIndex++;
					// handlingSteering = false;
					// aiDirection = Direction.DOWN;
					setAiDirection(Direction.DOWN);
				}
			} else if (targetIndex == 2) {
				if (handlingSteering) {
					if (car.getAngle() >= Math.PI * 3 / 2) {
						handlingSteering = false;
						targetIndex++;
					}
				}
				// else {
				// if (car.getX4rot() < target.getPointX(targetIndex)) {
				// car.setRIGHT(true);
				// car.setLEFT(false);
				// } else if (car.getX3rot() > target.getPointX(targetIndex)) {
				// car.setRIGHT(false);
				// car.setLEFT(true);
				// } else {
				// car.setRIGHT(false);
				// car.setLEFT(false);
				// }
				// if (Math.min(car.getY4rot(), car.getY3rot()) <
				// target.getPointX(targetIndex)) {
				// targetIndex++;
				// }
				// }
			} else {
				// aiDirection = Direction.DOWN;
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 8:
			if (targetIndex == 0) {
				if (car.getX4rot() > target.getPointX(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getX3rot() < target.getPointX(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.max(car.getY4rot(), car.getY3rot()) > target.getPointY(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if (Math.max(car.getY4rot(), car.getY3rot()) + steeringDistance < target.getPointY(targetIndex)) {
					if (car.getX4rot() > target.getPointX(targetIndex)) {
						car.setRIGHT(true);
						car.setLEFT(false);
					} else if (car.getX3rot() < target.getPointX(targetIndex)) {
						car.setRIGHT(false);
						car.setLEFT(true);
					} else {
						car.setRIGHT(false);
						car.setLEFT(false);
					}
				} else {
					if (car.getSpeed() > AXELONTURNING)
						brake();
					// accel();
					car.setRIGHT(false);
					car.setLEFT(true);
					handlingSteering = true;
				}
				if (handlingSteering) {
					targetIndex++;
					// handlingSteering = false;
					aiDirection = Direction.RIGHT;
				}
			} else if (targetIndex == 2) {
				if (handlingSteering) {
					if (car.getAngle() <= Math.PI / 12 || car.getAngle() >= Math.PI * 3 / 2) {
						handlingSteering = false;
						// targetIndex++;
					}
				} else {
					if (car.getY4rot() < target.getPointX(targetIndex)) {
						car.setRIGHT(true);
						car.setLEFT(false);
					} else if (car.getY3rot() > target.getPointX(targetIndex)) {
						car.setRIGHT(false);
						car.setLEFT(true);
					} else {
						car.setRIGHT(false);
						car.setLEFT(false);
					}
					if (Math.max(car.getX4rot(), car.getX3rot()) >= target.getPointX(targetIndex)) {
						targetIndex++;
					}
				}
			} else {
				// aiDirection = Direction.DOWN;
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 9:
			if (targetIndex == 0) {
				if (car.getY4rot() < target.getPointY(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getY3rot() > target.getPointY(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.max(car.getX4rot(), car.getX3rot()) > target.getPointX(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if (car.getY4rot() < target.getPointY(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getY3rot() > target.getPointY(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.max(car.getX4rot(), car.getX3rot()) > target.getPointX(targetIndex)) {
					setAiDirection(Direction.RIGHT);
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 2) {
				if (car.getY4rot() < target.getPointY(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getY3rot() > target.getPointY(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.max(car.getX4rot(), car.getX3rot()) > target.getPointX(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else {
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 10:
			if (targetIndex == 0) {
				if (car.getY4rot() > target.getPointY(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getY3rot() < target.getPointY(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.min(car.getX4rot(), car.getX3rot()) < target.getPointX(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if (car.getY4rot() > target.getPointY(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getY3rot() < target.getPointY(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.min(car.getX4rot(), car.getX3rot()) < target.getPointX(targetIndex)) {
					setAiDirection(Direction.LEFT);
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 2) {
				if (car.getY4rot() > target.getPointY(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getY3rot() < target.getPointY(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.min(car.getX4rot(), car.getX3rot()) < target.getPointX(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else {
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 11:
			if (targetIndex == 0) {
				if (car.getX4rot() > target.getPointX(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getX3rot() < target.getPointX(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.max(car.getY4rot(), car.getY3rot()) > target.getPointY(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if (car.getX4rot() > target.getPointX(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getX3rot() < target.getPointX(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.max(car.getY4rot(), car.getY3rot()) > target.getPointY(targetIndex)) {
					setAiDirection(Direction.DOWN);
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 2) {
				if (car.getX4rot() > target.getPointX(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getX3rot() < target.getPointX(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.max(car.getY4rot(), car.getY3rot()) > target.getPointY(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else {
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		case 12:
			if (targetIndex == 0) {
				if (car.getX4rot() < target.getPointX(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getX3rot() > target.getPointX(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.min(car.getY4rot(), car.getY3rot()) < target.getPointY(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 1) {
				if (car.getX4rot() < target.getPointX(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getX3rot() > target.getPointX(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.min(car.getY4rot(), car.getY3rot()) < target.getPointY(targetIndex)) {
					setAiDirection(Direction.UP);
					targetIndex++;
					handlingSteering = false;
				}
			} else if (targetIndex == 2) {
				if (car.getX4rot() < target.getPointX(targetIndex)) {
					car.setRIGHT(true);
					car.setLEFT(false);
				} else if (car.getX3rot() > target.getPointX(targetIndex)) {
					car.setRIGHT(false);
					car.setLEFT(true);
				} else {
					car.setRIGHT(false);
					car.setLEFT(false);
				}
				if (Math.min(car.getY4rot(), car.getY3rot()) < target.getPointY(targetIndex)) {
					targetIndex++;
					handlingSteering = false;
				}
			} else {
				updateTargetCondition = true;
				targetIndex = 0;
			}
			break;
		default:
			System.err.println("ERRORE" + car.getID());
			break;
		}

		car.move(world);

	}

	public Direction getAiDirection() {
		try {
			lock.lock();
			return aiDirection;
		} finally {
			lock.unlock();
		}
	}

	public void setAiDirection(final Direction dir) {
		lock.lock();
		this.aiDirection = dir;
		lock.unlock();
	}

	private void accel() {
		car.setUP(true);
		car.setDOWN(false);

	}

	private void brake() {
		car.setUP(false);
		car.setDOWN(true);
	}

}
