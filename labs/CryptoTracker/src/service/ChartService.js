import axios from "axios";
import API_KEY from "./key";

class ChartService {
  static getChart(coin, period) {
    let interval;
    let startDate;

    // Set the interval and start date based on the selected period
    switch (period) {
      case "1DAY":
        interval = "h1"; // 1 hour intervals
        startDate = new Date(Date.now() - 24 * 60 * 60 * 1000);
        break;
      case "7DAY":
        interval = "h12";
        startDate = new Date(Date.now() - 7 * 24 * 60 * 60 * 1000);
        break;
      case "1MTH":
        interval = "h1";
        startDate = new Date(Date.now() - 30 * 24 * 60 * 60 * 1000);
        break;
      default:
        interval = "h1";
        startDate = new Date(Date.now() - 24 * 60 * 60 * 1000);
    }

    const endDate = new Date();

    return axios.get(`https://rest.coincap.io/v3/assets/${coin}/history`, {
      params: {
        interval,
        start: startDate.getTime(),
        end: endDate.getTime(),
      },
      headers: {
        Accept: "application/json",
        Authorization: `Bearer ${API_KEY}`,
      },
    });
  }
}

export default ChartService;
