import pickle
from itertools import chain, combinations


def powerset(s):
    return [frozenset(subset) for subset in list(chain.from_iterable(combinations(s, r) for r in range(1, len(s) + 1)))]

def search(symptoms):

	with open('rules.pkl','rb') as file:
		rules=pickle.load(file)

	with open('diseases.pkl','rb') as file:
		diseaseSet=pickle.load(file)
	
	diseases=set()

	for key in rules.keys():
		if symptoms.issubset(key):
			diseases.update(rules[key])
	
	if not diseases:
		symptomset_list=list(powerset(symptoms))
		for each in symptomset_list:
			if each in rules.keys():
				diseases.update(rules[each])
	diseases=list(diseases)
	
	symptomSet=list()
	for disease in diseases:
		symptomSet.append(list(diseaseSet[disease]))
	
	return {"diseases":diseases,"symptoms":symptomSet}	


if __name__=='__main__':
	#print(rules)
	symptoms=frozenset({"Antennae Cut", "Slow swimming","Abnormal growth"})
	print(list(powerset(symptoms)))
	print("after searching:\n",search(symptoms))