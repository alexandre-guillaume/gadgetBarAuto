package com.sopra;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.GaData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple example of how to access the Google Analytics API using a service
 * account.
 */
public class RequeteGA {

	private static final String APPLICATION_NAME = "Requete Google Analytics";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String SERVICE_ACCOUNT_EMAIL = "244928579941-kftdvta8vcfb652a19okeminim514gnj@developer.gserviceaccount.com";

	public static List<DonneesChart> getResponse(String path, String view, String metric) {

		String DIMENSIONS = "ga:date,ga:hour";

		List<DonneesChart> result = new ArrayList<>();
		List<List<String>> dataToday = null;
		List<List<String>> dataLastWeek = null;
		try {
			// On récupère les données d'aujourd'hui
			Analytics analytics = initializeAnalytics(path);
			GaData gadataToday = analytics.data().ga()
					.get(view, "today", "today", metric)
					.setDimensions(DIMENSIONS)
					.execute();
			if (gadataToday != null && !gadataToday.getRows().isEmpty()) {
				dataToday = gadataToday.getRows();
			}

			// On récupère les données de la semaine dernière
			GaData gadataLastWeek = analytics.data().ga()
					.get(view, "7daysAgo", "7daysAgo", metric)
					.setDimensions(DIMENSIONS)
					.execute();
			if (gadataLastWeek != null && !gadataLastWeek.getRows().isEmpty()) {
				dataLastWeek = gadataLastWeek.getRows();
			}

			for (int i = 0; i < dataToday.size() && i < dataLastWeek.size(); i++) { 
				// On ajoute chaque heure de la journée suivi du nombre de visiteurs correpondante aujourd'hui puis celle ce la semaine dernière
				result.add(new DonneesChart(dataToday.get(i).get(1) + "h", Integer.parseInt(dataToday.get(i).get(2)), Integer.parseInt(dataLastWeek.get(i).get(2))));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private static Analytics initializeAnalytics(String path) throws Exception {
		// Initializes an authorized analytics service object.

		// Construct a GoogleCredential object with the service account email
		// and p12 file downloaded from the developer console.
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleCredential credential = new GoogleCredential.Builder()
				.setTransport(httpTransport)
				.setJsonFactory(JSON_FACTORY)
				.setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
				.setServiceAccountPrivateKeyFromP12File(new File(path))
				.setServiceAccountScopes(AnalyticsScopes.all())
				.build();

		// Construct the Analytics service object.
		return new Analytics.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}


}