from flask import Flask, request, jsonify
from search import powerset,search
import pickle

app = Flask(__name__)

@app.route('/api/', methods=['POST'])
def get_values():
        print("API called")
        data = request.get_json()
        symptomset = data.get('symptoms', [])
        print(symptomset)
        print("type: ",type(symptomset))
        symptoms=frozenset(symptomset)
        print(symptoms)
        print("type: ",type(symptoms))
        print("pever",powerset(symptoms))
        response_data=search(symptoms)

        print(response_data)

        return jsonify(response_data)

if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0")
	