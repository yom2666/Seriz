package models;

public enum ShowStatus {
	
	NoInfo, Project, Pilot, Order, CommingSoon, CantFind, WaitForSubtitles, ToStart, ToResume, Cancelled, SeasonFinished, Finished, UnFollowed, Dropped;
	
	public String getString() {
		switch (this) {
		case Project:
			return "Projet";
		case Pilot:
			return "Pilote";
		case CommingSoon:
			return "Prochainement";
		case Order:
			return "Commandée";
		case CantFind:
			return "Pas Trouvé";
		case WaitForSubtitles:
			return "Manque srt";
		case ToStart:
			return "A commencer";
		case ToResume:
			return "A Reprendre";
		case Cancelled:
			return "Annulée";
		case Finished:
			return "Finie";
		case SeasonFinished:
			return "Saison terminée";
		case UnFollowed:
			return "Plus suivie";
		case Dropped:
			return "Abandonnée";
		default:
			return "No Info";
		}
	};

}
