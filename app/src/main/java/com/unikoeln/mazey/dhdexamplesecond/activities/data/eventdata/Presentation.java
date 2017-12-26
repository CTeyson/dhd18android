package com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata;

public class Presentation {

    private int presentationID;
    private String presentationContributionType;
    private String presentationAuthors;
    private String presentationOrganisation;
    private String getPresentationPresentingAuthors;
    private String presentationTitle;
    private String presentationAbstract;

    public Presentation(
            int presentationID,
            String presentationContributionType,
            String presentationAuthors,
            String presentationOrganisation,
            String getPresentationPresentingAuthors,
            String presentationTitle,
            String presentationAbstract) {
        this.presentationID = presentationID;
        this.presentationContributionType = presentationContributionType;
        this.presentationAuthors = presentationAuthors;
        this.presentationOrganisation = presentationOrganisation;
        this.getPresentationPresentingAuthors = getPresentationPresentingAuthors;
        this.presentationTitle = presentationTitle;
        this.presentationAbstract = presentationAbstract;
    }

    public int getPresentationID() {
        return presentationID;
    }

    public void setPresentationID(int presentationID) {
        this.presentationID = presentationID;
    }

    public String getPresentationContributionType() {
        return presentationContributionType;
    }

    public void setPresentationContributionType(String presentationContributionType) {
        this.presentationContributionType = presentationContributionType;
    }

    public String getPresentationAuthors() {
        return presentationAuthors;
    }

    public void setPresentationAuthors(String presentationAuthors) {
        this.presentationAuthors = presentationAuthors;
    }

    public String getPresentationOrganisation() {
        return presentationOrganisation;
    }

    public void setPresentationOrganisation(String presentationOrganisation) {
        this.presentationOrganisation = presentationOrganisation;
    }

    public String getGetPresentationPresentingAuthors() {
        return getPresentationPresentingAuthors;
    }

    public void setGetPresentationPresentingAuthors(String getPresentationPresentingAuthors) {
        this.getPresentationPresentingAuthors = getPresentationPresentingAuthors;
    }

    public String getPresentationTitle() {
        return presentationTitle;
    }

    public void setPresentationTitle(String presentationTitle) {
        this.presentationTitle = presentationTitle;
    }

    public String getPresentationAbstract() {
        return presentationAbstract;
    }

    public void setPresentationAbstract(String presentationAbstract) {
        this.presentationAbstract = presentationAbstract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Presentation that = (Presentation) o;

        if (presentationID != that.presentationID) return false;
        if (presentationContributionType != null ? !presentationContributionType.equals(that.presentationContributionType) : that.presentationContributionType != null)
            return false;
        if (presentationAuthors != null ? !presentationAuthors.equals(that.presentationAuthors) : that.presentationAuthors != null)
            return false;
        if (presentationOrganisation != null ? !presentationOrganisation.equals(that.presentationOrganisation) : that.presentationOrganisation != null)
            return false;
        if (getPresentationPresentingAuthors != null ? !getPresentationPresentingAuthors.equals(that.getPresentationPresentingAuthors) : that.getPresentationPresentingAuthors != null)
            return false;
        if (presentationTitle != null ? !presentationTitle.equals(that.presentationTitle) : that.presentationTitle != null)
            return false;
        return presentationAbstract != null ? presentationAbstract.equals(that.presentationAbstract) : that.presentationAbstract == null;
    }

    @Override
    public int hashCode() {
        int result = presentationID;
        result = 31 * result + (presentationContributionType != null ? presentationContributionType.hashCode() : 0);
        result = 31 * result + (presentationAuthors != null ? presentationAuthors.hashCode() : 0);
        result = 31 * result + (presentationOrganisation != null ? presentationOrganisation.hashCode() : 0);
        result = 31 * result + (getPresentationPresentingAuthors != null ? getPresentationPresentingAuthors.hashCode() : 0);
        result = 31 * result + (presentationTitle != null ? presentationTitle.hashCode() : 0);
        result = 31 * result + (presentationAbstract != null ? presentationAbstract.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Presentation{" +
                "presentationID=" + presentationID +
                ", presentationContributionType='" + presentationContributionType + '\'' +
                ", presentationAuthors=" + presentationAuthors +
                ", presentationOrganisation=" + presentationOrganisation +
                ", getPresentationPresentingAuthors=" + getPresentationPresentingAuthors +
                ", presentationTitle='" + presentationTitle + '\'' +
                ", presentationAbstract='" + presentationAbstract + '\'' +
                '}';
    }
}
