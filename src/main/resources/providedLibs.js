// Java libs
var ArrayList = Java.type("java.util.ArrayList");
var HashMap = Java.type("java.util.HashMap");
var Thread = Java.type("java.lang.Thread");

// Triggers
var TriggerRegister = Java.type("com.chattriggers.ctjs.triggers.TriggerRegister");
var TriggerResult = Java.type("com.chattriggers.ctjs.triggers.OnTrigger.TriggerResult");
var Priority = Java.type("com.chattriggers.ctjs.triggers.OnTrigger.Priority");

// Events
var ChatLib = Java.type("com.chattriggers.ctjs.libs.ChatLib");
var WorldLib = Java.type("com.chattriggers.ctjs.libs.WorldLib");
var RenderLib = Java.type("com.chattriggers.ctjs.libs.RenderLib");
var FileLib = Java.type("com.chattriggers.ctjs.libs.FileLib");
var MathLib = Java.type("com.chattriggers.ctjs.libs.MathLib");

// Objects
var Display = Java.type("com.chattriggers.ctjs.objects.Display");
var DisplayHandler = Java.type("com.chattriggers.ctjs.handlers.DisplayHandler");
var DisplayLine = Java.type("com.chattriggers.ctjs.objects.Display.DisplayLine");
var Gui = Java.type("com.chattriggers.ctjs.objects.Gui");
var Message = Java.type("com.chattriggers.ctjs.objects.Message");
var Book = Java.type("com.chattriggers.ctjs.objects.Book");
var KeyBind = Java.type("com.chattriggers.ctjs.objects.KeyBind");
var Keyboard = Java.type("org.lwjgl.input.Keyboard");
var XMLHttpRequest = Java.type("com.chattriggers.ctjs.objects.XMLHttpRequest");
var Console = Java.type("com.chattriggers.ctjs.utils.console.Console");
var LookingAt = Java.type("com.chattriggers.ctjs.objects.LookingAt");
var Inventory = Java.type("com.chattriggers.ctjs.objects.Inventory");
var CPS = Java.type("com.chattriggers.ctjs.CTJS").getInstance().getCps();
var TabList = Java.type("com.chattriggers.ctjs.objects.TabList");

/*Built in Vars */
var MinecraftVars = Java.type("com.chattriggers.ctjs.libs.MinecraftVars");
var ScoreboardReader = Java.type("com.chattriggers.ctjs.libs.ScoreboardReader");

// Constant
var playerName = MinecraftVars.getPlayerName();
var uuid = MinecraftVars.getPlayerUUID();
var mcVersion = MinecraftVars.getMinecraftVersion();

// Update every tick
var hp = MinecraftVars.getPlayerHP();
var hunger = MinecraftVars.getPlayerHunger();
var saturation = MinecraftVars.getPlayerSaturation();
var armorPoints = MinecraftVars.getPlayerArmorPoints();
var airLevel = MinecraftVars.getPlayerAirLevel()
var xpLevel = MinecraftVars.getXPLevel();
var xpProgress = MinecraftVars.getXPProgress();
var inChat = MinecraftVars.isInChat();
var inTab = MinecraftVars.isInTab();
var isSprinting = MinecraftVars.isSprinting();
var isSneaking = MinecraftVars.isSneaking();
var ping = MinecraftVars.getPing();
var posX = MinecraftVars.getPlayerPosX();
var posY = MinecraftVars.getPlayerPosY();
var posZ = MinecraftVars.getPlayerPosZ();
var motionX = MinecraftVars.getPlayerMotionX();
var motionY = MinecraftVars.getPlayerMotionY();
var motionZ = MinecraftVars.getPlayerMotionZ();
var biome = MinecraftVars.getPlayerBiome();
var lightLevel = MinecraftVars.getPlayerLightLevel();
var cameraPitch = MinecraftVars.getPlayerPitch();
var cameraYaw = MinecraftVars.getPlayerYaw();
var fps = MinecraftVars.getPlayerFPS();
var facing = MinecraftVars.getPlayerFacing();
var leftArrow = MinecraftVars.isLeftArrowDown();
var rightArrow = MinecraftVars.isRightArrowDown();
var upArrow = MinecraftVars.isUpArrowDown();
var downArrow = MinecraftVars.isDownArrowDown();
var tabbedIn = MinecraftVars.isUserTabbedIn();
var potEffects = MinecraftVars.getActivePotionEffects();
var maxMem = MinecraftVars.getMaxMemory();
var totalMem = MinecraftVars.getTotalMemory();
var freeMem = MinecraftVars.getFreeMemory();
var memUsage = MinecraftVars.getMemoryUsage();
var scoreboardTitle = ScoreboardReader.getScoreboardTitle();
var scoreboardNames = ScoreboardReader.getScoreboardNames();
var isFlying = MinecraftVars.isFlying();
var isSleeping = MinecraftVars.isSleeping();

// Update every world load
var serverIP = MinecraftVars.getServerIP();
var serverMOTD = MinecraftVars.getServerMOTD();
var server = MinecraftVars.getServerName();

/*End Built in Vars */

function updateProvidedLibsTick() {
    hp = MinecraftVars.getPlayerHP();
    hunger = MinecraftVars.getPlayerHunger();
    saturation = MinecraftVars.getPlayerSaturation();
    armorPoints = MinecraftVars.getPlayerArmorPoints();
    airLevel = MinecraftVars.getPlayerAirLevel();
    xpLevel = MinecraftVars.getXPLevel();
    xpProgress = MinecraftVars.getXPProgress();
    inChat = MinecraftVars.isInChat();
    inTab = MinecraftVars.isInTab();
    isSprinting = MinecraftVars.isSprinting();
    isSneaking = MinecraftVars.isSneaking();
    ping = MinecraftVars.getPing();
    posX = MinecraftVars.getPlayerPosX();
    posY = MinecraftVars.getPlayerPosY();
    posZ = MinecraftVars.getPlayerPosZ();
    motionX = MinecraftVars.getPlayerMotionX();
    motionY = MinecraftVars.getPlayerMotionY();
    motionZ = MinecraftVars.getPlayerMotionZ();
    biome = MinecraftVars.getPlayerBiome();
    lightLevel = MinecraftVars.getPlayerLightLevel();
    cameraPitch = MinecraftVars.getPlayerPitch();
    cameraYaw = MinecraftVars.getPlayerYaw();
    fps = MinecraftVars.getPlayerFPS();
    facing = MinecraftVars.getPlayerFacing();
    leftArrow = MinecraftVars.isLeftArrowDown();
    rightArrow = MinecraftVars.isRightArrowDown();
    upArrow = MinecraftVars.isUpArrowDown();
    downArrow = MinecraftVars.isDownArrowDown();
    tabbedIn = MinecraftVars.isUserTabbedIn();
    potEffects = MinecraftVars.getActivePotionEffects();
    maxMem = MinecraftVars.getMaxMemory();
    totalMem = MinecraftVars.getTotalMemory();
    freeMem = MinecraftVars.getFreeMemory();
    memUsage = MinecraftVars.getMemoryUsage();
    ScoreboardReader.resetCache();
    scoreboardTitle = ScoreboardReader.getScoreboardTitle();
    scoreboardNames = ScoreboardReader.getScoreboardNames();
    isFlying = MinecraftVars.isFlying();
    isSleeping = MinecraftVars.isSleeping();

    LookingAt.update();
}

function updateProvidedLibsWorld() {
    serverIP = MinecraftVars.getServerIP();
    serverMOTD = MinecraftVars.getServerMOTD();
    server = MinecraftVars.getServerName();
}

// simplified methods
function print(toPrint) {
    Console.getConsole().out.println(toPrint);
}

function cancel(event) {
    event.setCanceled(true);
}

function easeOut(start, finish, speed, jump) {
    if (jump == undefined) {jump = 1;}
	if (Math.floor(Math.abs(finish - start) / jump)) {
		return start + (finish - start) / speed;
	} else {
		return finish;
	}
}